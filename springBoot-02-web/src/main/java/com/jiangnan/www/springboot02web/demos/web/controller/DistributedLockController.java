package com.jiangnan.www.springboot02web.demos.web.controller;

import com.jiangnan.www.springboot02web.demos.web.service.OrderService;
import com.jiangnan.www.springboot02web.demos.web.service.RedissonLockService;
import com.jiangnan.www.springboot02web.demos.web.util.DistributedLockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 分布式锁测试控制器
 *
 * @author jiangNan
 */
@RestController
@RequestMapping("/lock")
public class DistributedLockController {

    @Autowired
    private RedissonLockService lockService;

    @Autowired
    private DistributedLockUtil lockUtil;

    @Autowired
    private OrderService orderService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 测试 Redisson 分布式锁
     * GET /lock/test?key=xxx
     */
    @GetMapping("/test")
    public Map<String, Object> testLock(@RequestParam(defaultValue = "testLock") String key) {
        Map<String, Object> result = new HashMap<>();

        boolean isLocked = lockService.tryLock(key, 10);
        if (isLocked) {
            try {
                result.put("success", true);
                result.put("message", "获取锁成功");
                result.put("lockKey", key);
                result.put("isLocked", lockService.isLocked(key));

                // 模拟业务处理
                Thread.sleep(100);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                result.put("success", false);
                result.put("message", "业务处理被中断");
            } finally {
                lockService.unlock(key);
                result.put("released", true);
            }
        } else {
            result.put("success", false);
            result.put("message", "获取锁失败");
        }

        return result;
    }

    /**
     * 模拟秒杀场景（使用分布式锁）
     * GET /lock/seckill
     */
    @GetMapping("/seckill")
    public Map<String, Object> seckill() throws ExecutionException, InterruptedException {
        // 重置库存
        orderService.resetStock(100);

        int taskCount = 150; // 150 个请求，只有 100 个能成功
        CountDownLatch latch = new CountDownLatch(taskCount);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);

        long startTime = System.currentTimeMillis();

        // 提交 150 个并发请求
        for (int i = 0; i < taskCount; i++) {
            final int userId = i;
            executorService.submit(() -> {
                try {
                    String result = orderService.placeOrder("user_" + userId);
                    if (result.contains("成功")) {
                        successCount.incrementAndGet();
                    } else {
                        failCount.incrementAndGet();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        // 等待所有任务完成
        latch.await();
        long endTime = System.currentTimeMillis();

        Map<String, Object> result = new HashMap<>();
        result.put("总请求数", taskCount);
        result.put("成功数", successCount.get());
        result.put("失败数", failCount.get());
        result.put("剩余库存", orderService.getStock());
        result.put("耗时 (ms)", (endTime - startTime));
        result.put("说明", "使用分布式锁后，不会有超卖现象");

        return result;
    }

    /**
     * 不使用分布式锁的秒杀（对比测试）
     * GET /lock/seckill-no-lock
     */
    @GetMapping("/seckill-no-lock")
    public Map<String, Object> seckillNoLock() throws ExecutionException, InterruptedException {
        // 重置库存
        orderService.resetStock(100);

        int taskCount = 150;
        CountDownLatch latch = new CountDownLatch(taskCount);
        AtomicInteger successCount = new AtomicInteger(0);

        long startTime = System.currentTimeMillis();

        // 提交 150 个并发请求（不使用锁）
        for (int i = 0; i < taskCount; i++) {
            final int userId = i;
            executorService.submit(() -> {
                try {
                    // 直接扣减库存，不使用锁
                    int currentStock = orderService.getStock();
                    if (currentStock > 0) {
                        try {
                            Thread.sleep(10); // 模拟业务延迟
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        orderService.resetStock(currentStock - 1);
                        successCount.incrementAndGet();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        // 等待所有任务完成
        latch.await();
        long endTime = System.currentTimeMillis();

        Map<String, Object> result = new HashMap<>();
        result.put("总请求数", taskCount);
        result.put("成功数", successCount.get());
        result.put("剩余库存", orderService.getStock());
        result.put("耗时 (ms)", (endTime - startTime));
        result.put("说明", "不使用分布式锁，可能出现超卖（成功数>100 或库存<0）");

        return result;
    }

    /**
     * 检查锁状态
     * GET /lock/status?key=xxx
     */
    @GetMapping("/status")
    public Map<String, Object> checkLockStatus(@RequestParam(defaultValue = "testLock") String key) {
        Map<String, Object> result = new HashMap<>();
        result.put("lockKey", key);
        result.put("isLocked", lockService.isLocked(key));
        result.put("isHeldByCurrentThread", lockService.isHeldByCurrentThread(key));
        return result;
    }

    /**
     * 手动释放锁
     * GET /lock/unlock?key=xxx
     */
    @GetMapping("/unlock")
    public Map<String, Object> unlock(@RequestParam String key) {
        Map<String, Object> result = new HashMap<>();
        lockService.unlock(key);
        result.put("success", true);
        result.put("message", "锁已释放");
        result.put("lockKey", key);
        return result;
    }
}
