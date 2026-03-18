package com.jiangnan.www.springboot02web.demos.web.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redisson 分布式锁服务
 *
 * @author jiangNan
 */
@Service
public class RedissonLockService {

    private static final Logger logger = LoggerFactory.getLogger(RedissonLockService.class);

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 尝试获取锁（非阻塞）
     *
     * @param lockKey 锁的 key
     * @param leaseTime 锁持有时间（秒），-1 表示不过期
     * @return true-获取成功，false-获取失败
     */
    public boolean tryLock(String lockKey, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLocked;
        try {
            if (leaseTime > 0) {
                isLocked = lock.tryLock(leaseTime, TimeUnit.SECONDS);
            } else {
                isLocked = lock.tryLock();
            }
            logger.info("获取锁 [{}] 结果：{}", lockKey, isLocked ? "成功" : "失败");
            return isLocked;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("获取锁被中断：{}", lockKey, e);
            return false;
        }
    }

    /**
     * 尝试获取锁（带等待时间）
     *
     * @param lockKey 锁的 key
     * @param waitTime 最大等待时间（秒）
     * @param leaseTime 锁持有时间（秒）
     * @return true-获取成功，false-获取失败
     */
    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean isLocked = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
            logger.info("获取锁 [{}] 结果：{}", lockKey, isLocked ? "成功" : "失败");
            return isLocked;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("获取锁被中断：{}", lockKey, e);
            return false;
        }
    }

    /**
     * 获取锁（阻塞直到获取成功）
     *
     * @param lockKey 锁的 key
     */
    public void lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        logger.info("已获取锁：{}", lockKey);
    }

    /**
     * 获取锁（带超时）
     *
     * @param lockKey 锁的 key
     * @param leaseTime 锁持有时间（秒）
     */
    public void lock(String lockKey, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(leaseTime, TimeUnit.SECONDS);
        logger.info("已获取锁：{}，持有时间：{}秒", lockKey, leaseTime);
    }

    /**
     * 释放锁
     *
     * @param lockKey 锁的 key
     */
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
            logger.info("释放锁：{}", lockKey);
        }
    }

    /**
     * 检查锁是否被持有
     *
     * @param lockKey 锁的 key
     * @return true-锁被持有，false-锁未被持有
     */
    public boolean isLocked(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.isLocked();
    }

    /**
     * 检查锁是否被当前线程持有
     *
     * @param lockKey 锁的 key
     * @return true-锁被当前线程持有，false-否则
     */
    public boolean isHeldByCurrentThread(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.isHeldByCurrentThread();
    }
}
