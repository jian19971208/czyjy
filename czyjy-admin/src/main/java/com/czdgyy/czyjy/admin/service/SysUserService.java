package com.czdgyy.czyjy.admin.service;

import com.czdgyy.czyjy.core.dto.req.sys.SysUserAddReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserLoginReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserQueryReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserUpdateReqDto;
import com.czdgyy.czyjy.core.dto.res.sys.SysUserLoginResDto;
import com.czdgyy.czyjy.core.dto.res.sys.SysUserQueryResDto;
import com.mybatisflex.core.paginate.Page;
import jakarta.validation.Valid;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
public interface SysUserService {

    Page<SysUserQueryResDto> pageListUser(SysUserQueryReqDto req);

	void addSysUser(SysUserAddReqDto req);

    void updateSysUser(SysUserUpdateReqDto req);

    void deleteSysUser(Long userId);

    SysUserLoginResDto login(SysUserLoginReqDto req);
}
