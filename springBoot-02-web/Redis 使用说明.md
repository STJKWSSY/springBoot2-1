# Spring Boot Redis 连接使用说明

## 一、添加的依赖

在 `pom.xml` 中已添加以下依赖：

```xml
<!-- Spring Data Redis -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<!-- 连接池 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
```

---

## 二、配置文件

在 `application.yml` 中的 Redis 配置：

```yaml
spring:
  redis:
    host: localhost          # Redis 服务器地址
    port: 6379               # Redis 端口
    password:                # 密码（无密码则留空）
    database: 0              # 数据库索引
    timeout: 10000ms         # 连接超时时间
    # 连接池配置
    lettuce:
      pool:
        max-active: 8        # 最大连接数
        max-wait: -1ms       # 最大阻塞等待时间
        max-idle: 8          # 最大空闲连接
        min-idle: 0          # 最小空闲连接
```

---

## 三、配置类

### RedisConfig.java

**位置：** `src/main/java/com/jiangnan/www/springboot02web/demos/web/config/RedisConfig.java`

配置了 `RedisTemplate`，使用 Jackson 进行序列化，支持存储复杂对象。

---

## 四、使用示例

### RedisController.java

**位置：** `src/main/java/com/jiangnan/www/springboot02web/demos/web/controller/RedisController.java`

#### API 接口列表

| 接口 | 方法 | 说明 | 示例 |
|------|------|------|------|
| `/redis/set` | GET | 设置字符串值 | `?key=name&value=zhangsan` |
| `/redis/setWithExpire` | GET | 设置带过期时间的值 | `?key=code&value=123&timeout=60` |
| `/redis/get` | GET | 获取值 | `?key=name` |
| `/redis/delete` | GET | 删除 key | `?key=name` |
| `/redis/hasKey` | GET | 判断 key 是否存在 | `?key=name` |
| `/redis/increment` | GET | 自增操作 | `?key=counter` |
| `/redis/hset` | GET | 设置 Hash 值 | `?key=user&field=name&value=zhangsan` |
| `/redis/hget` | GET | 获取 Hash 值 | `?key=user&field=name` |
| `/redis/hgetAll` | GET | 获取整个 Hash | `?key=user` |

---

## 五、在 Service 中使用

```java
@Service
public class UserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 缓存用户信息
    public void cacheUser(User user) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("user:" + user.getId(), user, 30, TimeUnit.MINUTES);
    }

    // 获取缓存的用户
    public User getCachedUser(Long userId) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return (User) operations.get("user:" + userId);
    }

    // 使用缓存注解
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        // 数据库查询
        return userDao.findById(id);
    }
}
```

---

## 六、测试步骤

### 1. 启动 Redis

```bash
# Windows (如果安装为服务)
net start Redis

# Linux/Mac
redis-server

# 或使用 Docker
docker run -d -p 6379:6379 --name redis redis:latest
```

### 2. 启动 Spring Boot 项目

```bash
mvn spring-boot:run
```

### 3. 测试接口

```bash
# 设置值
curl "http://localhost:8080/redis/set?key=test&value=hello"

# 获取值
curl "http://localhost:8080/redis/get?key=test"

# 设置过期时间 (60 秒)
curl "http://localhost:8080/redis/setWithExpire?key=code&value=123456&timeout=60"

# 自增计数
curl "http://localhost:8080/redis/increment?key=visitCount"

# Hash 操作
curl "http://localhost:8080/redis/hset?key=user&field=name&value=zhangsan"
curl "http://localhost:8080/redis/hget?key=user&field=name"
```

### 4. 使用 Redis CLI 验证

```bash
redis-cli

# 查看所有 key
keys *

# 查看某个值
get test

# 查看 TTL
ttl code
```

---

## 七、常见问题

### 1. 连接被拒绝

```
Caused by: io.lettuce.core.RedisConnectionException: Unable to connect to localhost:6379
```

**解决：** 确保 Redis 服务已启动

### 2. 序列化异常

如果遇到序列化问题，检查 `RedisConfig` 中的配置是否正确。

### 3. 中文乱码

确保使用 `StringRedisSerializer` 或正确的字符编码。

---

## 八、进阶用法

### 使用 @Cacheable 注解

1. 在启动类添加 `@EnableCaching`

```java
@SpringBootApplication
@EnableCaching
public class SpringBoot02WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot02WebApplication.class, args);
    }
}
```

2. 在 Service 方法上使用缓存注解

```java
@Service
public class UserService {

    // 缓存查询结果
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    // 更新缓存
    @CachePut(value = "users", key = "#user.id")
    public User updateUser(User user) {
        return userDao.update(user);
    }

    // 删除缓存
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}
```

---

## 九、文件清单

```
src/main/java/com/jiangnan/www/springboot02web/
├── demos/web/
│   ├── config/
│   │   ├── RedisConfig.java           (新增 - Redis 配置)
│   │   ├── WebConfig.java
│   │   └── MatrixVariableConfig.java
│   ├── controller/
│   │   ├── RedisController.java       (新增 - Redis 测试接口)
│   │   ├── LoginController.java
│   │   └── ...
│   └── interceptor/
│       └── LoginCheckInterceptor.java
└── ...
```

---

## 十、参考链接

* [Spring Data Redis](https://spring.io/projects/spring-data-redis)
* [Redis 官方文档](https://redis.io/documentation)
* [Lettuce 客户端](https://lettuce.io/)
