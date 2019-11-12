package com.thoughtworks.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Autowired
    private DataSourcePropertiesConfiguration dataSourceConfig;

    @Bean("dataSource")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataSourceConfig.getUrl());
        dataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
        dataSource.setUsername(dataSourceConfig.getUsername());
        dataSource.setPassword(dataSourceConfig.getPassword());
        dataSource.setMaximumPoolSize(dataSourceConfig.getMaxPoolSize());
        dataSource.setMinimumIdle(dataSourceConfig.getMinIdle());
        return dataSource;
    }
}
