package com.tsollu.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

public class TsolluDataSourceBuilder {

    @SuppressWarnings("unchecked")
    protected static <T> T createHikariDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }

    public static HikariDataSource createHikariDataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = createHikariDataSource(properties, HikariDataSource.class);
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

    public static DruidDataSource createDruidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

}
