package com.thoughtworks.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("customize.datasource")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataSourcePropertiesConfiguration {

    private String url;
    private String username;
    private String password;

    @Value("${customize.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${customize.datasource.dbcp2.max-total}")
    private int maxPoolSize;

    @Value("${customize.datasource.dbcp2.min-idle}")
    private int minIdle;

}
