package com.tsollu.mybatis.datasource;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author larry.qi
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class TsolluDataSourceAutoConfiguration {

    /**
     * Hikari DataSource configuration.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(HikariDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource",
        matchIfMissing = true)
    static class Hikari {

        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.hikari")
        HikariDataSource dataSource(DataSourceProperties properties) {
            return TsolluDataSourceBuilder.createHikariDataSource(properties);
        }

    }

}
