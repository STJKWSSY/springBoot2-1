package com.jiangnan.www.springboot02web.demos.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单服务 - 演示分布式锁的使用场景
 *
 * @author jiangNan
 */
@Service
public class OrderService {

    @Autowired
    private RedissonLockService lockService;

    // 模拟库存
    private volatile int stock = 100;

    /**
     * 秒杀下单（使用分布式锁保证线程安全）
     *
     * @param userId 用户 ID
     * @return 下单结果
     */
    public String placeOrder(String userId) {
        String lockKey = "order:lock:" + userId;

        // 尝试获取锁，等待 3 秒，持有 10 秒自动释放
        boolean isLocked = lockService.tryLock(lockKey, 3, 10);

        if (!isLocked) {
            return "下单失败：系统繁忙，请稍后再试";
        }

        try {
            // 检查库存
            if (stock <= 0) {
                return "下单失败：库存不足";
            }

            // 模拟业务处理时间
            Thread.sleep(100);

            // 扣减库存
            stock--;

            return "下单成功，剩余库存：" + stock;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "下单失败：订单处理中断";
        } finally {
            // 释放锁
            lockService.unlock(lockKey);
        }
    }

    /**
     * 获取当前库存
     */
    public int getStock() {
        return stock;
    }

    /**
     * 重置库存（用于测试）
     */
    public void resetStock(int stock) {
        this.stock = stock;
    }
}
