package com.kaddo.components.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Configuration(KaddoJpaAutoConfiguration.AUDITOR_AWARE_REF)
@ConditionalOnMissingBean(name = KaddoJpaAutoConfiguration.AUDITOR_AWARE_REF)
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("system");
    }

}
