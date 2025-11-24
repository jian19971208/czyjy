package com.czdgyy.czyjy.core.annotation.flex;

import com.czdgyy.czyjy.core.common.enums.OperatorEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lcj
 * @since 2025/11/24/周一
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryField {
	String key();
	OperatorEnum operator() default OperatorEnum.EQ;
}
