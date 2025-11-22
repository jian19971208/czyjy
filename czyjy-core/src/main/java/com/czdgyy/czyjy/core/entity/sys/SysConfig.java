package com.czdgyy.czyjy.core.entity.sys;

import com.czdgyy.czyjy.core.entity.BaseEntity;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置表
 * @author lcj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_config")
public class SysConfig extends BaseEntity {

	/**
	 * 配置名称
	 */
	private String configKey;

	/**
	 * 配置值
	 */
	private String configValue;

	/**
	 * 备注
	 */
	private String remark;

}

