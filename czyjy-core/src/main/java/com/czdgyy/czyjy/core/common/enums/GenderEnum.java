package com.czdgyy.czyjy.core.common.enums;

import lombok.Getter;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@Getter
public enum GenderEnum implements BaseEnum {

	/**
	 * 男
	 */
	MALE("MALE", "男"),
	/**
	 * 女
	 */
	FEMALE("FEMALE", "女"),
	/**
	 * 其他
	 */
	OTHER("OTHER", "其他");

	private final String code;
	private final String name;

	GenderEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
