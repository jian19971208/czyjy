package com.czdgyy.czyjy.core.common.response;

import lombok.Getter;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@Getter
public enum CommonResponseCode implements ResponseCode {

	SUCCESS(200, "成功"),
	UNAUTHORIZED(401, "未授权，请登录"),
	SERVER_ERROR(500, "服务异常")
	;

	private final Integer code;

	private final String message;

	CommonResponseCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}


}
