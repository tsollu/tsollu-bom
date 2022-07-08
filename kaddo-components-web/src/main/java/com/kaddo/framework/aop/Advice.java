package com.kaddo.framework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 抽象的 Advice 接口，提供使用 AOP 时的常用方法。
 */
public interface Advice {

    default Method getMethod(JoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod();
    }

    default Method getMethod(ProceedingJoinPoint proceedingJoinPoint) {
        return ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
    }

    default HttpServletRequest getHttpServletRequest() {
        return getServletRequestAttributes().getRequest();
    }

    default ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    default HttpServletResponse getHttpServletResponse() {
        return getServletRequestAttributes().getResponse();
    }

    default ServletContext getServletContext() {
        return getApplicationContext().getServletContext();
    }

    default WebApplicationContext getApplicationContext() {
        return ContextLoader.getCurrentWebApplicationContext();
    }

    void setPointcut();

}
