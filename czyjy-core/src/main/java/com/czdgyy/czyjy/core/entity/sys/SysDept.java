package com.czdgyy.czyjy.core.entity.sys;

import com.czdgyy.czyjy.core.entity.BaseEntity;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统部门表
 * @author Administrator
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_dept")
public class SysDept extends BaseEntity {

	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 状态编码
	 */
	private String deptStatusCode;

	/**
	 * 状态名称
	 */
	private String deptStatusName;

	/**
	 * 备注
	 */
	private String remark;

}


