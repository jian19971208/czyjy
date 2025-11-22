package com.czdgyy.czyjy.core.helper;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.czdgyy.czyjy.core.common.constants.WebConstants.HEADER_FORWARDED;

@Component
@RequiredArgsConstructor
public class RequestHelper {

    private final HttpServletRequest request;

    public String getIp() {
        String ip = request.getHeader(HEADER_FORWARDED);
        if (StrUtil.isEmpty(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
