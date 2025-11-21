package com.czdgyy.czyjy.core.annotation.jackson;

import java.lang.annotation.*;

/**
 * 清除小数点标志
 * @author lcj
 * @since 2024-03-28 15:25
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClearPoint {
}
