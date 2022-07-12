package com.tsollu.aop;

import org.aspectj.lang.JoinPoint;

/**
 * Advice that runs before a join point but that does not have the ability to prevent execution flow
 * proceeding to the join point (unless it throws an exception).
 */
public interface BeforeAdvice extends Advice {

    /**
     * 使用 @Before 注解声明前置通知。
     *
     * @param joinPoint 连接点
     */
    void before(JoinPoint joinPoint);

}
