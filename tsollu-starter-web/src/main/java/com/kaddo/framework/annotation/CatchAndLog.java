package com.kaddo.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CatchAndLog Annotation
 */
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CatchAndLog {

    /**
     * 接口描述信息
     *
     * @return 接口描述信息
     */
    String value() default "";

    /**
     * 是否打印请求和响应日志
     *
     * @return true 表示打印日志
     */
    boolean logWatch() default true;

    /**
     * 开启日志后，指定日志序列化的信息脱敏规则
     *
     * @return 未指定则使用默认规则
     */
    String beanName() default "";

    /**
     * 开启日志后，指定日志序列化时忽略的字段
     *
     * @return 忽略的字段
     */
    String[] ignoreFields() default {};

}
