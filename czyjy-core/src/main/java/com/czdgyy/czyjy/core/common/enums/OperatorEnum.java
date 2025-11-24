package com.czdgyy.czyjy.core.common.enums;

import lombok.Getter;

/**
 * @author lcj
 * @since 2025/11/24/周一
 */
@Getter
public enum OperatorEnum implements BaseEnum {
	/**
	 * 等于
	 */
	EQ("EQ", "="),
	/**
	 * 不等于
	 */
	NE("NE", "<>"),
	/**
	 * 包含
	 */
	IN("IN", "IN"),
	/**
	 * 不包含
	 */
	NOT_IN("NOT_IN", "NOT IN"),
	/**
	 * 大于等于
	 */
	GE("GE", ">="),
	/**
	 * 小于等于
	 */
	LE("LE", "<="),
	/**
	 * 大于
	 */
	GT("GT", ">"),
	/**
	 * 小于
	 */
	LT("LT", "<"),
	/**
	 * 模糊匹配
	 */
	LIKE("LIKE", "LIKE")
	;


	private final String code;
	private final String name;

	OperatorEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
