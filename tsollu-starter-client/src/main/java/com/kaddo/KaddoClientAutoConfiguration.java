package com.kaddo;

import com.kaddo.exception.GlobalExceptionHandler;
import com.kaddo.exception.GlobalExceptionTranslator;
import com.kaddo.exception.GlobalResponseBodyAdvice;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * KaddoClientAutoConfiguration
 *
 * @author larry.qi
 * @date 2022-07-02
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@Import({GlobalResponseBodyAdvice.class, GlobalExceptionTranslator.class, GlobalExceptionHandler.class})
public class KaddoClientAutoConfiguration {
}
