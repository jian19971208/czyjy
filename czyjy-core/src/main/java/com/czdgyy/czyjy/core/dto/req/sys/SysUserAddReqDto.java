package com.czdgyy.czyjy.core.dto.req.sys;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@Data
public class SysUserAddReqDto {
	/**
	 * 用户名
	 */
	@NotEmpty(message = "用户名不能为空")
	private String username;

	/**
	 * 密码
	 */
	@NotEmpty(message = "密码不能为空")
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
	 * 性别名称
	 */
	private String genderName;


	/**
	 * 备注
	 */
	private String remark;
}
