package com.tsollu.exception;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures. The
 * error response follows RFC7807 - Problem Details for HTTP APIs (https://tools.ietf.org/html/rfc7807)
 *
 * @author larry.qi
 * @date 2022-07-02
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(ProblemHandling.class)
@RestControllerAdvice
public class GlobalExceptionTranslator implements ProblemHandling {

}
