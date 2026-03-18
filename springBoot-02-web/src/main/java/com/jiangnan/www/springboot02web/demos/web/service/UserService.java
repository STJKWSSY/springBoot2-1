package com.jiangnan.www.springboot02web.demos.web.service;

import com.jiangnan.www.springboot02web.demos.web.pojo.User;
import com.jiangnan.www.springboot02web.demos.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务层
 * 处理业务逻辑
 *
 * @author jiangNan
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 创建用户
     * @param user 用户对象
     * @return 创建后的用户（包含生成的 ID）
     */
    public User createUser(User user) {
        // 业务验证
        if (user == null) {
            throw new IllegalArgumentException("用户对象不能为空");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名称不能为空");
        }
        if (user.getAge() != null && (user.getAge() < 0 || user.getAge() > 150)) {
            throw new IllegalArgumentException("年龄必须在 0-150 之间");
        }
        return userRepository.save(user);
    }

    /**
     * 根据 ID 查询用户
     * @param id 用户 ID
     * @return 用户对象
     */
    public User getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("用户 ID 不能为空");
        }
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在，ID: " + id);
        }
        return user;
    }

    /**
     * 查询所有用户
     * @return 用户列表
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 根据名称查询用户
     * @param name 用户名称（支持模糊查询）
     * @return 用户列表
     */
    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    /**
     * 更新用户
     * @param id 用户 ID
     * @param user 新的用户信息
     * @return 更新后的用户
     */
    public User updateUser(Long id, User user) {
        if (id == null) {
            throw new IllegalArgumentException("用户 ID 不能为空");
        }

        User existingUser = userRepository.findById(id);
        if (existingUser == null) {
            throw new RuntimeException("用户不存在，ID: " + id);
        }

        // 更新字段
        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getAge() != null) {
            if (user.getAge() < 0 || user.getAge() > 150) {
                throw new IllegalArgumentException("年龄必须在 0-150 之间");
            }
            existingUser.setAge(user.getAge());
        }

        return userRepository.update(existingUser);
    }

    /**
     * 删除用户
     * @param id 用户 ID
     * @return true-删除成功
     */
    public boolean deleteUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("用户 ID 不能为空");
        }
        if (!userRepository.exists(id)) {
            throw new RuntimeException("用户不存在，ID: " + id);
        }
        return userRepository.deleteById(id);
    }

    /**
     * 获取用户总数
     * @return 用户总数
     */
    public long getUserCount() {
        return userRepository.count();
    }
}
