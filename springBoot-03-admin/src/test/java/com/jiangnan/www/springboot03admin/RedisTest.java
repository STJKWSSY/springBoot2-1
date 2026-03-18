package com.jiangnan.www.springboot03admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * redis连接测试
 *
 * @author jiangNan
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    StringRedisTemplate redisTemplate;


    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Test
    void testRedis() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        operations.set("hello", "world");

        String hello = operations.get("hello");
        System.out.println(hello);
        // class org.springframework.data.redis.connection.jedis.JedisConnectionFactory
        System.out.println(redisConnectionFactory.getClass());
    }
}
