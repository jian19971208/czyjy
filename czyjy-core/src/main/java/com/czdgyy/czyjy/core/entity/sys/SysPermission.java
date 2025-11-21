package com.czdgyy.czyjy.core.entity.sys;

import com.czdgyy.czyjy.core.entity.BaseEntity;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统权限表
 * @author lcj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_permission")
public class SysPermission extends BaseEntity {

	/**
	 * 权限编码
	 */
	private String permissionCode;

	/**
	 * 权限名称
	 */
	private String permissionName;

	/**
	 * 备注
	 */
	private String remark;

}

