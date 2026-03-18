package com.jiangnan.www.springboot02web.demos.web.repository;

import com.jiangnan.www.springboot02web.demos.web.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 用户数据存储层（模拟数据库）
 * 使用 Map 模拟数据库表
 *
 * @author jiangNan
 */
@Repository
public class UserRepository {

    // 使用 Map 模拟数据库表，key 为 id，value 为 User 对象
    private final Map<Long, User> database = new HashMap<>();

    // 模拟自增 ID
    private final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * 初始化一些测试数据
     */
    public UserRepository() {
        save(new User(null, "张三", 25));
        save(new User(null, "李四", 30));
        save(new User(null, "王五", 28));
    }

    /**
     * 保存用户
     * @param user 用户对象
     * @return 保存后的用户（包含生成的 ID）
     */
    public User save(User user) {
        if (user.getId() == null) {
            // 新增用户，生成自增 ID
            Long id = idGenerator.getAndIncrement();
            user.setId(id);
        }
        database.put(user.getId(), user);
        return user;
    }

    /**
     * 根据 ID 查找用户
     * @param id 用户 ID
     * @return 用户对象，不存在返回 null
     */
    public User findById(Long id) {
        return database.get(id);
    }

    /**
     * 查找所有用户
     * @return 用户列表
     */
    public List<User> findAll() {
        return new ArrayList<>(database.values());
    }

    /**
     * 根据名称查找用户
     * @param name 用户名称
     * @return 用户列表
     */
    public List<User> findByName(String name) {
        List<User> result = new ArrayList<>();
        for (User user : database.values()) {
            if (user.getName().contains(name)) {
                result.add(user);
            }
        }
        return result;
    }

    /**
     * 更新用户
     * @param user 用户对象（必须包含 ID）
     * @return 更新后的用户，不存在返回 null
     */
    public User update(User user) {
        if (user.getId() == null || !database.containsKey(user.getId())) {
            return null;
        }
        database.put(user.getId(), user);
        return user;
    }

    /**
     * 删除用户
     * @param id 用户 ID
     * @return true-删除成功，false-删除失败（用户不存在）
     */
    public boolean deleteById(Long id) {
        return database.remove(id) != null;
    }

    /**
     * 检查用户是否存在
     * @param id 用户 ID
     * @return true-存在，false-不存在
     */
    public boolean exists(Long id) {
        return database.containsKey(id);
    }

    /**
     * 获取用户总数
     * @return 用户总数
     */
    public long count() {
        return database.size();
    }

    /**
     * 清空所有用户
     */
    public void clear() {
        database.clear();
        idGenerator.set(1);
    }
}
