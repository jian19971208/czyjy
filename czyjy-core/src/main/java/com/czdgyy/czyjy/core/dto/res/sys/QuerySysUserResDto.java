package com.czdgyy.czyjy.core.dto.res.sys;

import com.czdgyy.czyjy.core.common.response.CommonResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuerySysUserResDto extends CommonResult {
	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 部门ID
	 */
	private Long deptId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 状态编码
	 */
	private String adminStatusCode;

	/**
	 * 状态名称
	 */
	private String adminStatusName;

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
	 * 最后登录时间
	 */
	private LocalDateTime lastLoginTime;

	/**
	 * 最后登录IP
	 */
	private String lastLoginIp;

	/**
	 * 备注
	 */
	private String remark;
}
