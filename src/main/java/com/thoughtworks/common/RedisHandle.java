package com.thoughtworks.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisHandle {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value, long expired) {
        stringRedisTemplate.opsForValue().set(key, value, expired, TimeUnit.MINUTES);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
