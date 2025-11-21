package com.czdgyy.czyjy.core.utils;

import com.czdgyy.czyjy.core.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@Slf4j
public class FunctionUtil {

	private FunctionUtil() {

	}

	/**
	 * 条件为true时抛出异常
	 *
	 * @param condition 条件
	 * @param exceptionSupplier 异常提供者
	 * @param <E> 异常类型
	 * @throws E 当条件为true时抛出的异常
	 */
	public static <E extends RuntimeException> void throwIf(boolean condition, Supplier<E> exceptionSupplier)
	{
		if (condition) {
			throw exceptionSupplier.get();
		}
	}

	/**
	 * 执行操作并捕获异常，异常时记录日志并抛出自定义异常
	 *
	 * @param action 要执行的操作
	 * @param errorMessage 异常时的错误提示
	 */
	public static void tryRun(Runnable action, String errorMessage) {
		try {
			action.run();
		} catch (Exception e) {
			log.error("操作执行失败: {}", errorMessage, e);
			throw new BaseException(errorMessage);
		}
	}

	/**
	 * 执行操作并捕获异常，异常时记录日志并抛出自定义异常
	 *
	 * @param action 要执行的操作
	 * @param errorMessage 异常时的错误提示
	 * @param <T> 返回值类型
	 * @return 操作的返回值
	 */
	public static <T> T tryGet(Supplier<T> action, String errorMessage) {
		try {
			return action.get();
		} catch (Exception e) {
			log.error("操作执行失败: {}", errorMessage, e);
			throw new BaseException(errorMessage);
		}
	}
	
}
