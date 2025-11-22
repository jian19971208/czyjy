package com.czdgyy.czyjy.core.convert.sys;

import com.czdgyy.czyjy.core.dto.res.sys.SysUserLoginResDto;
import com.czdgyy.czyjy.core.entity.sys.SysPermission;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@Mapper(componentModel = "spring")
public interface SysPermissionConvert {

    List<SysUserLoginResDto.PermissionResDto> entityToLoginPermissionResDtoList(List<SysPermission> sysPermissionList);
}
