package com.czdgyy.czyjy.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@SpringBootApplication(scanBasePackages = "com.czdgyy.czyjy")
@MapperScan("com.czdgyy.czyjy.core.mapper")
public class CzyjyAdminApplication {
}
