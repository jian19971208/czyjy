package com.czdgyy.czyjy.admin.service.impl.sys;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.czdgyy.czyjy.admin.service.iface.sys.SysUserService;
import com.czdgyy.czyjy.core.common.enums.BaseEnum;
import com.czdgyy.czyjy.core.common.enums.CommonStatusEnum;
import com.czdgyy.czyjy.core.common.enums.GenderEnum;
import com.czdgyy.czyjy.core.common.response.CommonResponseCode;
import com.czdgyy.czyjy.core.convert.sys.SysPermissionConvert;
import com.czdgyy.czyjy.core.convert.sys.SysRoleConvert;
import com.czdgyy.czyjy.core.convert.sys.SysUserConvert;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserAddReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserLoginReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserQueryReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserUpdateReqDto;
import com.czdgyy.czyjy.core.dto.res.sys.SysUserLoginResDto;
import com.czdgyy.czyjy.core.dto.res.sys.SysUserQueryResDto;
import com.czdgyy.czyjy.core.entity.sys.SysPermission;
import com.czdgyy.czyjy.core.entity.sys.SysRole;
import com.czdgyy.czyjy.core.entity.sys.SysRolePermission;
import com.czdgyy.czyjy.core.entity.sys.SysUser;
import com.czdgyy.czyjy.core.exception.BaseException;
import com.czdgyy.czyjy.core.helper.RequestHelper;
import com.czdgyy.czyjy.core.repository.sys.*;
import com.mybatisflex.core.paginate.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

import static com.czdgyy.czyjy.core.utils.FunctionUtil.*;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserRepository sysUserRepository;
    private final SysUserConvert sysUserConvert;
    private final SysRoleRepository sysRoleRepository;
    private final SysRolePermissionRepository sysRolePermissionRepository;
    private final SysPermissionRepository sysPermissionRepository;
    private final SysRoleConvert sysRoleConvert;
    private final SysPermissionConvert sysPermissionConvert;
    private final SysConfigRepository sysConfigRepository;
    private final ExecutorService virtualThreadExecutor;
    private final RequestHelper requestHelper;

    @Override
    public Page<SysUserQueryResDto> pageListUser(SysUserQueryReqDto req) {
        return sysUserRepository.pageAs(req.toPage(), req.toQuery(), SysUserQueryResDto.class);
    }

    @Override
    public void addSysUser(SysUserAddReqDto req) {
        String username = req.getUsername();
        //先判断是否有同名，防止唯一索引报错用户看不懂
        boolean exist = sysUserRepository.queryChain()
                .eq(SysUser::getUsername, username)
                .exists();
        Assert.isTrue(!exist, "用户名已存在：" + username);
        //然后就来生成新用户
        SysUser sysUser = sysUserConvert.addReqDtoToEntity(req);
        this.validHandleSuperAdmin(sysUser);
        //加密密码
        String encryptPassword = BCrypt.hashpw(req.getPassword());
        sysUser.setPassword(encryptPassword);
        this.handleEnumCode(sysUser);
        tryRun(() -> sysUserRepository.save(sysUser), "新增用户失败");
    }

    @Override
    public void updateSysUser(SysUserUpdateReqDto req) {
        //查询数据
        SysUser sysUser = sysUserRepository.getById(req.getUserId());
        Assert.notNull(sysUser, StrUtil.format("用户不存在:{}", req.getUserId()));
        //转换数据
        SysUser update = sysUserConvert.updateReqDtoToEntity(req);
        this.validHandleSuperAdmin(sysUser);
        update.setVersion(sysUser.getVersion());
        //改密码的话要重新加密
        if (StrUtil.isNotBlank(req.getPassword())) {
            String encryptPassword = BCrypt.hashpw(req.getPassword());
            update.setPassword(encryptPassword);
        }
        //不允许手动指定超管，超管只有一个账号
        if (Objects.nonNull(req.getRoleId())) {
            SysRole sysRole = sysRoleRepository.getSuperAdminRole();
            throwIf(Objects.equals(req.getRoleId(), sysRole.getId()), () -> new BaseException(CommonResponseCode.FORBIDDEN));
        }
        this.handleEnumCode(update);
        tryRun(() -> sysUserRepository.updateById(update), "更新用户失败");
    }

    @Override
    public void deleteSysUser(Long userId) {
        SysUser sysUser = sysUserRepository.getById(userId);
        Assert.notNull(sysUser, StrUtil.format("用户不存在:{}", userId));
        //超管不允许删除
        this.validHandleSuperAdmin(sysUser);
        tryRun(() -> sysUserRepository.removeById(userId), "删除用户失败");
    }

    @Override
    public SysUserLoginResDto login(SysUserLoginReqDto req) {
        String username = req.getUsername();
        SysUser sysUser = sysUserRepository.queryChain()
                .eq(SysUser::getUsername, username)
                .one();
        Assert.notNull(sysUser, StrUtil.format("用户不存在：{}", username));
        //校验密码
        boolean passwordMatch = BCrypt.checkpw(req.getPassword(), sysUser.getPassword());
        Assert.isTrue(passwordMatch, "用户名或密码错误");
        //查询角色表
        SysRole sysRole = sysRoleRepository.getById(sysUser.getRoleId());
        Assert.notNull(sysRole, "用户角色不存在,请重新分配");
        //查询角色权限关系表
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.queryChain()
                .eq(SysRolePermission::getRoleId, sysUser.getRoleId())
                .list();
        List<Long> permissionIdList = sysRolePermissionList.stream().map(SysRolePermission::getPermissionId).toList();
        //查询具体的权限
        List<SysPermission> sysPermissionList = sysPermissionRepository.queryChain()
                .in(SysPermission::getId, permissionIdList, CollectionUtil.isNotEmpty(permissionIdList))
                .list();
        //转换返回结果
        SysUserLoginResDto res = new SysUserLoginResDto();
        SysUserLoginResDto.UserResDto userResDto = sysUserConvert.entityToLoginUserResDto(sysUser);
        res.setUser(userResDto);
        //转化角色
        SysUserLoginResDto.RoleResDto roleResDto = sysRoleConvert.entityToLoginRoleResDto(sysRole);
        res.setRole(roleResDto);
        if (CollectionUtil.isNotEmpty(sysPermissionList)) {
            List<SysUserLoginResDto.PermissionResDto> permissionResDtoList = sysPermissionConvert.entityToLoginPermissionResDtoList(sysPermissionList);
            res.setPermissions(permissionResDtoList);
        }
        StpUtil.login(sysUser.getId());
        res.setToken(StpUtil.getTokenValue());
        //异步刷新数据
        virtualThreadExecutor.execute(() -> this.flushUser(sysUser));
        return res;
    }

    private void flushUser(SysUser sysUser) {
        //刷新IP和登录时间
        SysUser update = new SysUser();
        update.setId(sysUser.getId());
        update.setVersion(sysUser.getVersion());
        update.setLastLoginIp(requestHelper.getIp());
        update.setLastLoginTime(LocalDateTime.now());
        tryRun(() -> sysUserRepository.updateById(update), "刷新用户登录信息失败");
    }

    private void handleEnumCode(SysUser sysUser) {
        //判断状态
        if (Objects.nonNull(sysUser.getAccountStatusCode())) {
            CommonStatusEnum statusEnum = BaseEnum.getByCode(CommonStatusEnum.class, sysUser.getAccountStatusCode());
            Assert.notNull(statusEnum, StrUtil.format("用户状态不合法：{}", sysUser.getAccountStatusCode()));
            sysUser.setAccountStatusName(statusEnum.getName());
        }
        //判断性别
        if (Objects.nonNull(sysUser.getGenderCode())) {
            GenderEnum genderEnum = BaseEnum.getByCode(GenderEnum.class, sysUser.getGenderCode());
            Assert.notNull(genderEnum, StrUtil.format("用户性别不合法：{}", sysUser.getGenderCode()));
            sysUser.setGenderName(genderEnum.getName());
        }
    }

    private void validHandleSuperAdmin(SysUser sysUser) {
        SysRole sysRole = sysRoleRepository.getSuperAdminRole();
        throwIf(Objects.equals(sysUser.getRoleId(), sysRole.getId()), () -> new BaseException(CommonResponseCode.FORBIDDEN));
    }
}
