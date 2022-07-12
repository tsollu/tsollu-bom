package com.kaddo.framework.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    public static Object getBean(String name) {
        assertContextInjected();
        return applicationContext.getBean(name);
    }

    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new RuntimeException("ApplicationContext can not be found in Spring Container");
        }
    }

}
