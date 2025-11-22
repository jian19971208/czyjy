package com.czdgyy.czyjy.core.convert.sys;

import com.czdgyy.czyjy.core.dto.res.sys.SysUserLoginResDto;
import com.czdgyy.czyjy.core.entity.sys.SysRole;
import org.mapstruct.Mapper;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@Mapper(componentModel = "spring")

public interface SysRoleConvert {
    SysUserLoginResDto.RoleResDto entityToLoginRoleResDto(SysRole sysRole);
}
