package com.czdgyy.czyjy.core.exception;

import com.czdgyy.czyjy.core.common.response.CommonResponseCode;
import com.czdgyy.czyjy.core.common.response.ResponseCode;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
public class BaseException extends RuntimeException implements ResponseCode {

	private final Integer code;

	private final String message;

    public BaseException(CommonResponseCode commonResponseCode) {
        super(commonResponseCode.getMessage());
        this.code = commonResponseCode.getCode();
        this.message = commonResponseCode.getMessage();
    }

	public BaseException(Integer code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public BaseException(String message) {
		super(message);
		this.code = CommonResponseCode.SERVER_ERROR.getCode();
		this.message = message;
	}


	@Override
	public Integer getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
