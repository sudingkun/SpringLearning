package com.we.springboot.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author: sudingkun
 * @date: 2019-08-16 9:14
 */
@Configuration
public class RedisSessionConfig {

    /**
     * session 序列化方式
     * @return
     */
    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer<Object> defaultRedisSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }

}
