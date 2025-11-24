package com.czdgyy.czyjy.admin.controller.web.sys;

import com.czdgyy.czyjy.admin.service.iface.sys.SysRoleService;
import com.czdgyy.czyjy.admin.service.iface.sys.SysUserService;
import com.czdgyy.czyjy.core.common.response.ResponseResult;
import com.czdgyy.czyjy.core.dto.req.sys.SysRoleAddReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysRoleQueryReqDto;
import com.czdgyy.czyjy.core.dto.res.sys.SysRoleQueryResDto;
import com.mybatisflex.core.paginate.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.czdgyy.czyjy.core.common.constants.WebConstants.WEB_ADMIN_PATH;

/**
 * @author lcj
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(WEB_ADMIN_PATH + "/sys/role")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @PostMapping("/pageList")
    public ResponseResult<Page<SysRoleQueryResDto>> pageListRole(@RequestBody SysRoleQueryReqDto req) {
        return ResponseResult.success(sysRoleService.pageListRole(req));
    }

    @PostMapping("/add")
    public ResponseResult<Void> addSysRole(@RequestBody @Valid SysRoleAddReqDto req) {
        sysRoleService.addSysRole(req);
        return ResponseResult.success();
    }


}
