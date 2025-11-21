package com.czdgyy.czyjy.core.config.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

import static com.czdgyy.czyjy.core.common.constants.WebConstants.TRACE;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@Component
public class TraceIdInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
		String traceId = UUID.randomUUID().toString().replace("-", "");
		MDC.put(TRACE, traceId);
		return true;
	}

	@Override
	public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) {
		try {
			MDC.remove(TRACE);
		} finally {
			MDC.clear();
		}
	}

}
