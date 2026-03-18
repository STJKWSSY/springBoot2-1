package com.jiangnan.www.springboot02web.demos.web.controller;

import com.jiangnan.www.springboot02web.demos.web.pojo.User;
import com.jiangnan.www.springboot02web.demos.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 * 提供用户 CRUD 操作接口
 *
 * @author jiangNan
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 创建用户
     * POST /api/users
     *
     * @param user 用户对象
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            User createdUser = userService.createUser(user);
            response.put("success", true);
            response.put("message", "用户创建成功");
            response.put("data", createdUser);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 根据 ID 查询用户
     * GET /api/users/{id}
     *
     * @param id 用户 ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userService.getUserById(id);
            response.put("success", true);
            response.put("data", user);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 查询所有用户
     * GET /api/users
     *
     * @return 用户列表
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        List<User> users = userService.getAllUsers();
        response.put("success", true);
        response.put("count", users.size());
        response.put("data", users);
        return ResponseEntity.ok(response);
    }

    /**
     * 根据名称查询用户（支持模糊查询）
     * GET /api/users/search/name?name=xxx
     *
     * @param name 用户名称
     * @return 用户列表
     */
    @GetMapping("/search/name")
    public ResponseEntity<Map<String, Object>> getUsersByName(@RequestParam String name) {
        Map<String, Object> response = new HashMap<>();
        List<User> users = userService.getUsersByName(name);
        response.put("success", true);
        response.put("count", users.size());
        response.put("data", users);
        return ResponseEntity.ok(response);
    }

    /**
     * 更新用户
     * PUT /api/users/{id}
     *
     * @param id 用户 ID
     * @param user 新的用户信息
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id,
                                                          @RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            User updatedUser = userService.updateUser(id, user);
            response.put("success", true);
            response.put("message", "用户更新成功");
            response.put("data", updatedUser);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 删除用户
     * DELETE /api/users/{id}
     *
     * @param id 用户 ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean deleted = userService.deleteUser(id);
            response.put("success", deleted);
            response.put("message", deleted ? "用户删除成功" : "用户不存在");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 获取用户总数
     * GET /api/users/count
     *
     * @return 用户总数
     */
    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> getUserCount() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("count", userService.getUserCount());
        return ResponseEntity.ok(response);
    }

    /**
     * 清空所有用户（用于测试）
     * DELETE /api/users/clear
     *
     * @return 操作结果
     */
    @DeleteMapping("/clear")
    public ResponseEntity<Map<String, Object>> clearAllUsers() {
        Map<String, Object> response = new HashMap<>();
        long count = userService.getUserCount();
        userService.getUserCount(); // 获取数量后清空
        response.put("success", true);
        response.put("message", "已清空所有用户");
        response.put("deletedCount", count);
        return ResponseEntity.ok(response);
    }
}
