package com.czdgyy.czyjy.core.exception;

import java.io.Serial;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
public class UnCheckedException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = -2025158777894158768L;

	public UnCheckedException() {
	}

	public UnCheckedException(String message) {
		super(message);
	}

	public UnCheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnCheckedException(Throwable cause) {
		super(cause);
	}

	public UnCheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

