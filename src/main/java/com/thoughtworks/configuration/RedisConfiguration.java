package com.thoughtworks.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RedisConfiguration {

    @Autowired
    private RedisPropertiesConfiguration redisPropertiesConfiguration;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration(
                redisPropertiesConfiguration.getMaster(), redisPropertiesConfiguration.getNodes());
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisPropertiesConfiguration.getMaxTotal());
        jedisPoolConfig.setMinIdle(redisPropertiesConfiguration.getMinIdle());
        jedisPoolConfig.setMaxIdle(redisPropertiesConfiguration.getMaxIdle());
        jedisPoolConfig.setMaxTotal(redisPropertiesConfiguration.getMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(redisPropertiesConfiguration.getMaxWaitingTime());
        JedisConnectionFactory jedisConnectionFactory =
                new JedisConnectionFactory(redisSentinelConfiguration);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;

    }

    @Bean
    RedisSentinelConnection redisSentinelConnection(JedisConnectionFactory jedisConnectionFactory) {
        return jedisConnectionFactory.getSentinelConnection();
    }

    @Bean
    RedisTemplate<Object, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        return new StringRedisTemplate(jedisConnectionFactory);
    }
}
