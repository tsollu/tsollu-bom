package com.kaddo.framework.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(KaddoProperties.class)
public class KaddoAutoConfiguration {

}
