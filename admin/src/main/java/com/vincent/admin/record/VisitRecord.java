package com.vincent.admin.record;

import com.vincent.admin.enums.UserOperation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vincent Tsai
 * @date 2021/4/16 22:45
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitRecord {

    String value() default "";

    UserOperation operation() default UserOperation.VISIT_PAGE;

    boolean isSave() default true;
}
