package com.we.springboot.shiro.config;

import org.crazycake.shiro.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * redisCacheManager 自定义对value的序列化方式
 * 也可以自己实现RedisSerializer重写方法
 * @author: sudingkun
 * @date: 2019-08-22 14:49
 */
public class MyRedisSerializer extends GenericJackson2JsonRedisSerializer implements RedisSerializer<Object> {

}
