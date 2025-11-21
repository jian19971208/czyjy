package com.czdgyy.czyjy.core.common.response;



import com.czdgyy.czyjy.core.common.constants.WebConstants;
import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.MDC;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@Data
@Accessors(chain = true)
public class ResponseResult<T> implements Serializable {
	@Serial
	private static final long serialVersionUID = -3709646657505034891L;
	/**
	 * 是否成功
	 */
	private boolean success;

	/**
	 * 响应码
	 */
	private Integer code;
	/**
	 * 响应消息
	 */
	private String message;
	/**
	 * 响应数据
	 */
	private T data;

	/**
	 * traceId - 直接写traceId会被覆盖
	 */
	private String tid;

	public ResponseResult(){
		this.tid = getTraceId();
	}

	private ResponseResult(ResponseCode responseCode, T data) {
		Objects.requireNonNull(responseCode, "响应码不能为空");
		this.tid = getTraceId();
		this.code = responseCode.getCode();
		this.message = responseCode.getMessage();
		this.data = data;
		this.success = responseCode.equals(CommonResponseCode.SUCCESS);
	}

	public static String getTraceId(){
		return MDC.get(WebConstants.TRACE);
	}

	public static <T> ResponseResult<T> success(){
		return new ResponseResult<>(CommonResponseCode.SUCCESS, null);
	}

	public static <T> ResponseResult<T> success(T data){
		return new ResponseResult<>(CommonResponseCode.SUCCESS, data);
	}

	public static <T> ResponseResult<T> fail(){
		return new ResponseResult<>(CommonResponseCode.SERVER_ERROR, null);
	}

	public static <T> ResponseResult<T> fail(T data){
		return new ResponseResult<>(CommonResponseCode.SERVER_ERROR, data);
	}

	public static <T> ResponseResult<T> failed(String message) {
		return new ResponseResult<T>(CommonResponseCode.SERVER_ERROR, null).setMessage(message);
	}

	public static <T> ResponseResult<T> failed(ResponseCode responseCode) {
		return failed(responseCode, responseCode.getMessage());
	}

	public static <T> ResponseResult<T> failed(ResponseCode responseCode, String message) {
		return new ResponseResult<T>(responseCode, null).setMessage(message);
	}

	public static <T> ResponseResult<T> failed(ResponseResult<?> response) {
		return ResponseResult.<T>failed(response.getMessage()).setCode(response.getCode());
	}


}
