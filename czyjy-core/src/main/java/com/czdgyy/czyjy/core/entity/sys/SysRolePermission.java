package com.czdgyy.czyjy.core.entity.sys;

import com.czdgyy.czyjy.core.entity.BaseEntity;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统角色权限表
 * @author lcj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_role_permission")
public class SysRolePermission extends BaseEntity {

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 权限ID
	 */
	private Long permissionId;

	/**
	 * 备注
	 */
	private String remark;

}

