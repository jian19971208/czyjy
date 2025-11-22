package com.czdgyy.czyjy.admin.controller.sys;

import com.czdgyy.czyjy.admin.service.SysUserService;
import com.czdgyy.czyjy.core.common.response.ResponseResult;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserAddReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserLoginReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserQueryReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserUpdateReqDto;
import com.czdgyy.czyjy.core.dto.res.sys.SysUserLoginResDto;
import com.czdgyy.czyjy.core.dto.res.sys.SysUserQueryResDto;
import com.mybatisflex.core.paginate.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/pageList")
    public ResponseResult<Page<SysUserQueryResDto>> pageListUser(@RequestBody SysUserQueryReqDto req) {
        return ResponseResult.success(sysUserService.pageListUser(req));
    }

    @PostMapping("/add")
	public ResponseResult<Void> addSysUser(@RequestBody @Valid SysUserAddReqDto req) {
		sysUserService.addSysUser(req);
		return ResponseResult.success();
	}

    @PostMapping("/update")
    public ResponseResult<Void> updateSysUser(@RequestBody @Valid SysUserUpdateReqDto req) {
        sysUserService.updateSysUser(req);
        return ResponseResult.success();
    }

    @PostMapping("/delete/{userId}")
    public ResponseResult<Void> deleteSysUser(@PathVariable Long userId) {
        sysUserService.deleteSysUser(userId);
        return ResponseResult.success();
    }

    @PostMapping("/login")
    public ResponseResult<SysUserLoginResDto> login(@RequestBody @Valid SysUserLoginReqDto req) {
        return ResponseResult.success(sysUserService.login(req));
    }



}
