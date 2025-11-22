package com.czdgyy.czyjy.core.dto.req.sys;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@Data
public class SysUserUpdateReqDto {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 部门ID
	 */
	private Long deptId;

	/**
	 * 状态编码
	 */
	private String accountStatusCode;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 性别编码
	 */
	private String genderCode;

	/**
	 * 备注
	 */
	private String remark;
}
