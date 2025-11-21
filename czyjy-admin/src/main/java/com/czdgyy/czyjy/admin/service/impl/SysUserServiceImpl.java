package com.czdgyy.czyjy.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.czdgyy.czyjy.admin.service.SysUserService;
import com.czdgyy.czyjy.core.convert.SysUserConvert;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserAddReqDto;
import com.czdgyy.czyjy.core.entity.sys.SysUser;
import com.czdgyy.czyjy.core.exception.BaseException;
import com.czdgyy.czyjy.core.repository.sys.SysUserRepository;
import com.czdgyy.czyjy.core.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
		//加密密码
		String encryptPassword = BCrypt.hashpw(req.getPassword());
		sysUser.setPassword(encryptPassword);
		tryRun(() -> sysUserRepository.save(sysUser), "新增用户失败");
	}
}
