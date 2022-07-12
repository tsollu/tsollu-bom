package com.tsollu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Kaddo. Properties are configured in the application.yml file.
 */
@ConfigurationProperties(prefix = "kaddo")
public class KaddoProperties {
}
