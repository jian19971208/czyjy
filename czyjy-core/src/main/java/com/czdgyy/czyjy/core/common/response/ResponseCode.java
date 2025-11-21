package com.czdgyy.czyjy.core.common.response;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
public interface ResponseCode {
	/**
	 * 获取响应码
	 * @return 响应码
	 */
	Integer getCode();
	/**
	 * 获取响应消息
	 * @return 响应消息
	 */
	String getMessage();
}
