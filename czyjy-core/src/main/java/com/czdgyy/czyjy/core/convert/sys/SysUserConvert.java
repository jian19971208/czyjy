package com.czdgyy.czyjy.core.convert.sys;


import com.czdgyy.czyjy.core.dto.req.sys.SysUserAddReqDto;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserUpdateReqDto;
import com.czdgyy.czyjy.core.dto.res.sys.SysUserLoginResDto;
import com.czdgyy.czyjy.core.entity.sys.SysUser;
import org.mapstruct.Mapper;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@Mapper(componentModel = "spring")
public interface SysUserConvert {

	SysUser addReqDtoToEntity(SysUserAddReqDto reqDto);

    SysUser updateReqDtoToEntity(SysUserUpdateReqDto req);

    SysUserLoginResDto.UserResDto entityToLoginUserResDto(SysUser sysUser);
}
