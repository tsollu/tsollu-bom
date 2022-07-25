package com.tsollu.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;

/**
 * @author larry.qi
 */
@Configuration(proxyBeanMethods = false)
@EnableDubbo
public class TsolluDubboAutoConfiguration {

}
