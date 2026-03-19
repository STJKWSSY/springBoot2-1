package com.jiangnan.www.springboot03admin.demos.web.actuator.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyComHealthIndicator extends AbstractHealthIndicator {

    @Value("${health.status}")
    private Boolean status;

    /**
     * 真实的检查方法
     *
     * @param builder 健康构建器
     *
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) {
        //mongodb。  获取连接进行测试
        Map<String, Object> map = new HashMap<>();
        // 检查完成
        if (status) {
            //            builder.up(); // 健康简略写法
            //            Status UNKNOWN = new Status("UNKNOWN");
            //            Status UP = new Status("UP");
            //            Status DOWN = new Status("DOWN");
            //            Status OUT_OF_SERVICE = new Status("OUT_OF_SERVICE");
            builder.status(Status.UP); // 更加详细
            map.put("count", 1);
            map.put("ms", 100);
        } else {
//            builder.down(); // 不健康
            builder.status(Status.OUT_OF_SERVICE);
            map.put("err", "连接超时");
            map.put("ms", 3000);
        }


        builder.withDetail("code", 100)
                .withDetails(map);

    }
}
