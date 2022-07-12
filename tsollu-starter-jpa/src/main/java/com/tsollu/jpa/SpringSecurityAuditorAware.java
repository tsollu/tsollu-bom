package com.tsollu.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Configuration(TsolluJpaAutoConfiguration.AUDITOR_AWARE_REF)
@ConditionalOnMissingBean(name = TsolluJpaAutoConfiguration.AUDITOR_AWARE_REF)
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("system");
    }

}
