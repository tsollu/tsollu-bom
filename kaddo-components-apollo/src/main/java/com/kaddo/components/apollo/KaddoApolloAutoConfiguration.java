package com.kaddo.components.apollo;

import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class KaddoApolloAutoConfiguration implements ApplicationContextAware, ConfigChangeListener {

    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        KaddoApolloAutoConfiguration.applicationContext = applicationContext;
    }

    @Override
    @ApolloConfigChangeListener
    public void onChange(@NonNull ConfigChangeEvent event) {
        event.changedKeys().forEach(key -> {
            ConfigChange configChange = event.getChange(key);
            log.info("Apollo ConfigChangeEvent - {}", configChange);
        });
        getApplicationContext().publishEvent(new EnvironmentChangeEvent(event.changedKeys()));
    }

}