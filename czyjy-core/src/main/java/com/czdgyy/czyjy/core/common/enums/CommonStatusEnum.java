package com.czdgyy.czyjy.core.common.enums;

import lombok.Getter;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@Getter
public enum CommonStatusEnum {

	/**
	 * 禁用
	 */
	DISABLE("DISABLE", "禁用"),
	/**
	 * 启用
	 */
	ENABLE("ENABLE", "启用");

	private final String code;
	private final String name;

	CommonStatusEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
