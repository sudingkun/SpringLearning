package com.we.springboot.starter.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 分表自定义注释
 *
 * @author we
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableNameIntercept {

    /**
     * 需要替换的表名列表
     */
    String[] tableName() default {};

    /**
     * 忽略替换表的方法
     */
    String[] ignoreMethod() default {};

}