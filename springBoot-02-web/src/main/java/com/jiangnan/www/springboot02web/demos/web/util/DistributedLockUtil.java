package com.jiangnan.www.springboot02web.demos.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis 分布式锁工具类（基于 Redisson）
 *
 * @author jiangNan
 */
@Component
public class DistributedLockUtil {

    private static final Logger logger = LoggerFactory.getLogger(DistributedLockUtil.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 尝试获取锁
     *
     * @param lockKey 锁的 key
     * @param expireTime 锁的过期时间（秒）
     * @return true-获取成功，false-获取失败
     */
    public boolean tryLock(String lockKey, long expireTime) {
        Boolean success = redisTemplate.opsForValue()
                .setIfAbsent(lockKey, getCurrentTime(), expireTime, TimeUnit.SECONDS);
        return success != null && success;
    }

    /**
     * 尝试获取锁（带等待时间）
     *
     * @param lockKey 锁的 key
     * @param waitTime 等待时间（秒）
     * @param leaseTime 锁持有时间（秒）
     * @return true-获取成功，false-获取失败
     */
    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < waitTime * 1000) {
            if (tryLock(lockKey, leaseTime)) {
                return true;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param lockKey 锁的 key
     */
    public void unlock(String lockKey) {
        redisTemplate.delete(lockKey);
        logger.info("释放锁：{}", lockKey);
    }

    /**
     * 获取当前时间戳（作为锁的值）
     */
    private String getCurrentTime() {
        return String.valueOf(System.currentTimeMillis());
    }
}
