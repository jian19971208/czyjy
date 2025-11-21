package com.czdgyy.czyjy.admin.controller.sys;

import com.czdgyy.czyjy.admin.service.SysUserService;
import com.czdgyy.czyjy.core.common.response.ResponseResult;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserAddReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.czdgyy.czyjy.core.common.constants.WebConstants.ADMIN_WEB_PATH;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN_WEB_PATH + "/sys/user")
public class SysUserController {

	private final SysUserService sysUserService;

	@PostMapping("/add")
	public ResponseResult<Void> addSysUser(@RequestBody @Validated SysUserAddReqDto req) {
		sysUserService.addSysUser(req);
		return ResponseResult.success();
	}


}
