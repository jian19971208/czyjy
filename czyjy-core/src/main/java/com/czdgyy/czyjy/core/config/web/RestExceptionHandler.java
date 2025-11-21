package com.czdgyy.czyjy.core.config.web;

import cn.dev33.satoken.exception.NotLoginException;
import com.czdgyy.czyjy.core.common.response.ResponseResult;
import com.czdgyy.czyjy.core.exception.BaseException;
import com.mybatisflex.core.exception.MybatisFlexException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static com.czdgyy.czyjy.core.common.response.CommonResponseCode.SERVER_ERROR;
import static com.czdgyy.czyjy.core.common.response.CommonResponseCode.UNAUTHORIZED;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

	// 处理所有异常
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseResult<Void>> handleException(Exception ex) {
		log.error("未知异常", ex);
		return new ResponseEntity<>(ResponseResult.failed(SERVER_ERROR, ex.getMessage()), HttpStatus.OK);
	}

	//处理基础异常
	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ResponseResult<Void>> handleBaseException(BaseException ex) {
		log.error("基础异常: {}", ex.getMessage());
		return new ResponseEntity<>(ResponseResult.failed(ex, ex.getMessage()), HttpStatus.OK);
	}

	// 处理 @Validated 校验异常（参数校验失败）
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseResult<Void>> handleValidationException(MethodArgumentNotValidException ex) {
		ex.getBindingResult().getFieldError();
		String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
		log.error("接口参数校验异常：{}", errorMessage);
		return new ResponseEntity<>(ResponseResult.failed(errorMessage), HttpStatus.OK);
	}

	// 处理 @Validated 校验异常（绑定错误）
	@ExceptionHandler(BindException.class)
	public ResponseEntity<ResponseResult<Void>> handleBindException(BindException ex) {
		ex.getBindingResult().getFieldError();
		String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
		log.error("接口参数绑定异常：{}", errorMessage);
		return new ResponseEntity<>(ResponseResult.failed(errorMessage), HttpStatus.OK);
	}

	//处理未登录异常
	@ExceptionHandler(NotLoginException.class)
	public ResponseEntity<ResponseResult<Void>> handleNotLoginException(NotLoginException ex) {
		log.error("未登录异常: {}", ex.getMessage());
		return new ResponseEntity<>(ResponseResult.failed(UNAUTHORIZED), HttpStatus.OK);
	}
}
