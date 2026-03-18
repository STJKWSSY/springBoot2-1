package com.jiangnan.www.springboot03admin.demos.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangnan.www.springboot03admin.demos.web.pojo.Result;
import com.jiangnan.www.springboot03admin.demos.web.pojo.SysAccount;
import com.jiangnan.www.springboot03admin.demos.web.pojo.User;
import com.jiangnan.www.springboot03admin.demos.web.service.SysAccountService;
import com.jiangnan.www.springboot03admin.demos.web.service.UserService;
import com.jiangnan.www.springboot03admin.demos.web.utils.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户controller
 *
 * @author jiangNan
 */
@RestController
@RequestMapping("/api/user")
public class UserController {


    /**
     * 用户服务
     */
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * user分页
     */
    @GetMapping("/page")
    public Result<IPage<User>> page(@RequestBody User user, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Result<IPage<User>> result = new Result<>();
        LambdaQueryWrapper<User> queryChainWrapper = new LambdaQueryWrapper<>();
        queryChainWrapper.like((user.getUserName() != null && !user.getUserName().isEmpty()), User::getUserName, user.getUserName())
                .eq(oConvertUtils.isNotEmpty(user.getAge()), User::getAge, user.getAge());
        Page<User> userPage = userService.page(new Page<>(pageNo, pageSize), queryChainWrapper);
        result.setData(userPage);
        result.setMessage("查询成功");
        return result;
    }

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 执行结果 Result<Object>
     */
    @PostMapping("/create")
    public Result<Object> create(@RequestBody User user) {
        boolean result = userService.save(user);
        if (!result) {
            return Result.error("添加失败", user);
        }
        return Result.ok(result);
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return Result<Object> 执行结果
     */
    @PostMapping("/update")
    public Result<Object> update(@RequestBody User user) {
        if (user == null) {
            return Result.error("修改失败", user);
        }
        userService.updateById(user);
        return Result.ok(user);
    }

    /**
     * 删除用户
     *
     * @param id 主键 ID
     * @return 受影响的行数
     */
    @GetMapping("/deleteById/{id}")
    public Result<Object> deleteById(@PathVariable Integer id) {
        boolean result = userService.removeById(id);
        if (!result) {
            return Result.error("删除失败", id);
        }
        return Result.ok(result);
    }

    /**
     * 根据主键 ID 查询一个系统账户
     *
     * @param id 主键 ID
     * @return 账户对象
     */
    @GetMapping("/findById/{id}")
    public Result<Object> findById(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user != null) {
            return Result.ok(user);
        } else {
            return Result.error("查找数据失败", id);
        }
    }

    /**
     * 查询所有系统账户
     *
     * @return 账户对象列表
     */
    @GetMapping("/findAll")
    public Result<Object> findAll() {
        List<User> users = userService.list();
        return Result.ok(users);
    }
}