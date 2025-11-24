package com.czdgyy.czyjy.admin.service.iface.sys;

import com.czdgyy.czyjy.core.dto.req.sys.SysRoleAddReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysRoleQueryReqDto;
import com.czdgyy.czyjy.core.dto.res.sys.SysRoleQueryResDto;
import com.mybatisflex.core.paginate.Page;
import jakarta.validation.Valid;

/**
 * @author lcj
 */
public interface SysRoleService {

    void addSysRole(SysRoleAddReqDto req);

    Page<SysRoleQueryResDto> pageListRole(SysRoleQueryReqDto req);
}
