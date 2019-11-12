package com.thoughtworks.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "customize.redis.sentinel")
@Data
public class RedisPropertiesConfiguration {

    private String master;

    private Set<String> nodes;

    @Value("${customize.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${customize.redis.pool.min-idle}")
    private int minIdle;

    @Value("${customize.redis.pool.max-total}")
    private int maxTotal;

    @Value("${customize.redis.pool.max-active}")
    private int maxActive;

    @Value("${customize.redis.pool.max-wait}")
    private int maxWaitingTime;

}
