# User CRUD 接口使用说明

## 一、功能概述

实现了完整的用户增删改查（CRUD）功能，采用标准的 RESTful API 设计风格。

---

## 二、项目结构

```
src/main/java/com/jiangnan/www/springboot02web/demos/web/
├── controller/
│   └── UserController.java          # 用户控制器（API 接口）
├── service/
│   └── UserService.java             # 用户服务层（业务逻辑）
├── repository/
│   └── UserRepository.java          # 用户数据层（数据存储）
└── pojo/
    └── User.java                    # 用户实体类
```

---

## 三、API 接口列表

### 3.1 创建用户

| 属性 | 值 |
|------|-----|
| **接口** | `/api/users` |
| **方法** | POST |
| **请求体** | JSON 对象 |

**请求示例：**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name": "张三", "age": 25}'
```

**响应示例：**
```json
{
  "success": true,
  "message": "用户创建成功",
  "data": {
    "id": 4,
    "name": "张三",
    "age": 25
  }
}
```

---

### 3.2 查询所有用户

| 属性 | 值 |
|------|-----|
| **接口** | `/api/users` |
| **方法** | GET |

**请求示例：**
```bash
curl http://localhost:8080/api/users
```

**响应示例：**
```json
{
  "success": true,
  "count": 3,
  "data": [
    {"id": 1, "name": "张三", "age": 25},
    {"id": 2, "name": "李四", "age": 30},
    {"id": 3, "name": "王五", "age": 28}
  ]
}
```

---

### 3.3 根据 ID 查询用户

| 属性 | 值 |
|------|-----|
| **接口** | `/api/users/{id}` |
| **方法** | GET |

**请求示例：**
```bash
curl http://localhost:8080/api/users/1
```

**响应示例：**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "张三",
    "age": 25
  }
}
```

---

### 3.4 根据名称查询用户（模糊查询）

| 属性 | 值 |
|------|-----|
| **接口** | `/api/users/search/name?name=xxx` |
| **方法** | GET |

**请求示例：**
```bash
curl "http://localhost:8080/api/users/search/name?name=张"
```

**响应示例：**
```json
{
  "success": true,
  "count": 1,
  "data": [
    {"id": 1, "name": "张三", "age": 25}
  ]
}
```

---

### 3.5 更新用户

| 属性 | 值 |
|------|-----|
| **接口** | `/api/users/{id}` |
| **方法** | PUT |
| **请求体** | JSON 对象 |

**请求示例：**
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name": "张三三", "age": 26}'
```

**响应示例：**
```json
{
  "success": true,
  "message": "用户更新成功",
  "data": {
    "id": 1,
    "name": "张三三",
    "age": 26
  }
}
```

---

### 3.6 删除用户

| 属性 | 值 |
|------|-----|
| **接口** | `/api/users/{id}` |
| **方法** | DELETE |

**请求示例：**
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

**响应示例：**
```json
{
  "success": true,
  "message": "用户删除成功"
}
```

---

### 3.7 获取用户总数

| 属性 | 值 |
|------|-----|
| **接口** | `/api/users/count` |
| **方法** | GET |

**请求示例：**
```bash
curl http://localhost:8080/api/users/count
```

**响应示例：**
```json
{
  "success": true,
  "count": 3
}
```

---

## 四、数据模型

### User 对象

| 字段 | 类型 | 说明 |
|------|------|------|
| id | Long | 用户 ID（自动生成） |
| name | String | 用户名称（必填） |
| age | Integer | 用户年龄（可选，范围 0-150） |

---

## 五、错误处理

### 常见错误响应

**400 Bad Request - 参数错误：**
```json
{
  "success": false,
  "message": "用户名称不能为空"
}
```

**404 Not Found - 资源不存在：**
```json
{
  "success": false,
  "message": "用户不存在，ID: 999"
}
```

---

## 六、验证规则

| 字段 | 规则 | 错误信息 |
|------|------|----------|
| name | 不能为空 | "用户名称不能为空" |
| age | 0-150 之间 | "年龄必须在 0-150 之间" |

---

## 七、测试数据

系统启动时会自动初始化 3 个测试用户：

| ID | 名称 | 年龄 |
|----|------|-----|
| 1 | 张三 | 25 |
| 2 | 李四 | 30 |
| 3 | 王五 | 28 |

---

## 八、完整测试流程

```bash
# 1. 查询所有用户
curl http://localhost:8080/api/users

# 2. 根据 ID 查询用户
curl http://localhost:8080/api/users/1

# 3. 创建新用户
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name": "赵六", "age": 35}'

# 4. 更新用户
curl -X PUT http://localhost:8080/api/users/4 \
  -H "Content-Type: application/json" \
  -d '{"name": "赵六六", "age": 36}'

# 5. 模糊查询
curl "http://localhost:8080/api/users/search/name?name=赵"

# 6. 获取用户总数
curl http://localhost:8080/api/users/count

# 7. 删除用户
curl -X DELETE http://localhost:8080/api/users/4

# 8. 清空所有用户（测试用）
curl -X DELETE http://localhost:8080/api/users/clear
```

---

## 九、文件清单

```
src/main/java/com/jiangnan/www/springboot02web/
├── demos/web/
│   ├── controller/
│   │   ├── UserController.java       (已更新 - 完整 CRUD)
│   │   ├── RedisController.java
│   │   ├── DistributedLockController.java
│   │   └── LoginController.java
│   ├── service/
│   │   ├── UserService.java          (新增)
│   │   ├── RedissonLockService.java
│   │   └── OrderService.java
│   ├── repository/
│   │   └── UserRepository.java       (新增)
│   ├── pojo/
│   │   └── User.java                 (已更新 - 添加 id 字段)
│   └── util/
│       └── DistributedLockUtil.java
└── ...
```

---

## 十、扩展建议

### 1. 集成数据库

目前使用 Map 模拟数据库，实际项目中可以替换为：

- **MyBatis / MyBatis-Plus**
- **JPA / Hibernate**
- **JdbcTemplate**

### 2. 添加分页功能

```java
@GetMapping
public ResponseEntity<Map<String, Object>> getUsers(
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "10") int size) {
    // 分页查询逻辑
}
```

### 3. 添加统一响应封装

```java
@Data
public class Result<T> {
    private boolean success;
    private String message;
    private T data;
    private long timestamp;
}
```

### 4. 添加日志记录

```java
@Slf4j
@Service
public class UserService {
    // 使用 log.info() 记录操作日志
}
```

---

## 十一、参考链接

* [RESTful API 设计最佳实践](https://restfulapi.net/)
* [Spring Boot REST Controller](https://spring.io/guides/gs/rest-service/)
* [HTTP 方法规范](https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Methods)
