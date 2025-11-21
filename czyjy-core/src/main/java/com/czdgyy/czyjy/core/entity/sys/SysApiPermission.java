package com.czdgyy.czyjy.core.entity.sys;

import com.czdgyy.czyjy.core.entity.BaseEntity;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统接口权限表
 * @author lcj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_api_permission")
public class SysApiPermission extends BaseEntity {

	/**
	 * 接口路径
	 */
	private String apiPath;

	/**
	 * 权限ID
	 */
	private Long permissionId;

	/**
	 * 备注
	 */
	private String remark;

}

