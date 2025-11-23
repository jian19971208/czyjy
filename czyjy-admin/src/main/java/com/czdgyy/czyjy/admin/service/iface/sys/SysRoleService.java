package com.czdgyy.czyjy.admin.service.iface.sys;

import com.czdgyy.czyjy.core.dto.req.sys.SysRoleAddReqDto;
import jakarta.validation.Valid;

public interface SysRoleService {

    void addSysRole(SysRoleAddReqDto req);
}
