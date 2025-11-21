package com.czdgyy.czyjy.app.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.czdgyy.czyjy.core.common.constants.SysConstants.ROLE_SUPER_ADMIN;

/**
 * @author lcj
 * @since 2025/4/15/周二
 */
@Slf4j
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器
        registry.addInterceptor(new SaInterceptor(_ -> {
            //先校验登录
            StpUtil.checkLogin();
            //然后做权限校验,超管默认拥有所有权限
            boolean isSuperAdmin = StpUtil.hasRole(ROLE_SUPER_ADMIN);
            if (!isSuperAdmin) {
                //非超管就需要校验接口的权限
                SaRequest request = SaHolder.getRequest();
                String requestPath = request.getRequestPath();
                Assert.isTrue(StrUtil.isNotEmpty(requestPath), "无效的请求路径");

            }
        })).addPathPatterns("/**");
    }

    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico")
                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(_ -> {
                    SaHolder.getResponse()
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", "*")
                            // 允许所有请求方式
                            .setHeader("Access-Control-Allow-Methods", "*")
                            // 允许的header参数
                            .setHeader("Access-Control-Allow-Headers", "*")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                    ;
                    // 如果是预检请求，则立即返回到前端
                    SaRouter.match(SaHttpMethod.OPTIONS).back();
                });
    }
}
