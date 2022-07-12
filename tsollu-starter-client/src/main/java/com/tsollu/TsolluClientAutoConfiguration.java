package com.tsollu;

import com.tsollu.exception.GlobalExceptionHandler;
import com.tsollu.exception.GlobalExceptionTranslator;
import com.tsollu.exception.GlobalResponseBodyAdvice;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * TsolluClientAutoConfiguration
 *
 * @author larry.qi
 * @date 2022-07-02
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@Import({GlobalResponseBodyAdvice.class, GlobalExceptionTranslator.class, GlobalExceptionHandler.class})
public class TsolluClientAutoConfiguration {
}
