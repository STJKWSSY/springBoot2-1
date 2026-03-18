package com.jiangnan.www.springboot02web.demos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * Redis 测试控制器
 * 演示 Redis 的基本操作
 *
 * @author jiangNan
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置字符串值
     * GET /redis/set?key=xxx&value=xxx
     */
    @GetMapping("/set")
    public String set(@RequestParam String key, @RequestParam String value) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        return "success";
    }

    /**
     * 设置带过期时间的值
     * GET /redis/setWithExpire?key=xxx&value=xxx&timeout=60
     */
    @GetMapping("/setWithExpire")
    public String setWithExpire(@RequestParam String key, @RequestParam String value,
                                @RequestParam(defaultValue = "60") long timeout) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value, timeout, TimeUnit.SECONDS);
        return "success";
    }

    /**
     * 获取值
     * GET /redis/get?key=xxx
     */
    @GetMapping("/get")
    public Object get(@RequestParam String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 删除 key
     * GET /redis/delete?key=xxx
     */
    @GetMapping("/delete")
    public String delete(@RequestParam String key) {
        redisTemplate.delete(key);
        return "success";
    }

    /**
     * 判断 key 是否存在
     * GET /redis/hasKey?key=xxx
     */
    @GetMapping("/hasKey")
    public boolean hasKey(@RequestParam String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 自增操作
     * GET /redis/increment?key=xxx
     */
    @GetMapping("/increment")
    public Long increment(@RequestParam String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 设置 Hash 值
     * GET /redis/hset?key=xxx&field=xxx&value=xxx
     */
    @GetMapping("/hset")
    public String hset(@RequestParam String key, @RequestParam String field,
                       @RequestParam String value) {
        redisTemplate.opsForHash().put(key, field, value);
        return "success";
    }

    /**
     * 获取 Hash 值
     * GET /redis/hget?key=xxx&field=xxx
     */
    @GetMapping("/hget")
    public Object hget(@RequestParam String key, @RequestParam String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取整个 Hash
     * GET /redis/hgetAll?key=xxx
     */
    @GetMapping("/hgetAll")
    public Object hgetAll(@RequestParam String key) {
        return redisTemplate.opsForHash().entries(key);
    }
}
