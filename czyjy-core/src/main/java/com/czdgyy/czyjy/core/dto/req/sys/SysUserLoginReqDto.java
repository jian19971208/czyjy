package com.czdgyy.czyjy.core.dto.req.sys;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@Data
public class SysUserLoginReqDto {
    @NotEmpty(message = "用户名不能为空")
	private String username;
    @NotEmpty(message = "密码不能为空")
	private String password;
}
