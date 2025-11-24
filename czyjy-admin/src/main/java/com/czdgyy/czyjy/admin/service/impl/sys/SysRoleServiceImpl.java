package com.czdgyy.czyjy.admin.service.impl.sys;

import cn.hutool.core.util.StrUtil;
import com.czdgyy.czyjy.admin.service.iface.sys.SysRoleService;
import com.czdgyy.czyjy.core.convert.sys.SysRoleConvert;
import com.czdgyy.czyjy.core.dto.req.sys.SysRoleAddReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysRoleQueryReqDto;
import com.czdgyy.czyjy.core.dto.res.sys.SysRoleQueryResDto;
import com.czdgyy.czyjy.core.entity.sys.SysRole;
import com.czdgyy.czyjy.core.repository.sys.SysRoleRepository;
import com.czdgyy.czyjy.core.utils.FunctionUtil;
import com.mybatisflex.core.paginate.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.czdgyy.czyjy.core.utils.FunctionUtil.*;

/**
 * @author lcj
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleRepository sysRoleRepository;

    private final SysRoleConvert sysRoleConvert;

    @Override
    public void addSysRole(SysRoleAddReqDto req) {
        //先通过角色编码查找数据
        String roleCode = req.getRoleCode();
        boolean exist = sysRoleRepository.queryChain()
                .eq(SysRole::getRoleCode, roleCode)
                .exists();
        Assert.isTrue(!exist, StrUtil.format("角色编码已存在：{}", roleCode));
        //然后就来新增角色
        SysRole sysRole = sysRoleConvert.reqDtoToEntity(req);
        tryRun(() -> sysRoleRepository.save(sysRole), StrUtil.format("新增角色失败：{}", req));
    }

    @Override
    public Page<SysRoleQueryResDto> pageListRole(SysRoleQueryReqDto req) {
        return null;
    }
}
