package com.jiangnan.www.springboot03admin.demos.web.controller;

import com.jiangnan.www.springboot03admin.demos.web.pojo.Result;
import com.jiangnan.www.springboot03admin.demos.web.pojo.SysAccount;
import com.jiangnan.www.springboot03admin.demos.web.service.SysAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统账户 REST 控制器
 *
 * @author jiangNan
 */
@RestController
@RequestMapping("/api/sys-accounts")
public class SysAccountController {

    private final SysAccountService sysAccountService;

    @Autowired
    public SysAccountController(SysAccountService sysAccountService) {
        this.sysAccountService = sysAccountService;
    }

    /**
     * 创建一个新的系统账户
     *
     * @param sysAccount 账户对象
     * @return 受影响的行数
     */
    @PostMapping("/create")
    public Result<Object> create(@RequestBody SysAccount sysAccount) {
        int result = sysAccountService.insert(sysAccount);
        return Result.ok(result);
    }

    /**
     * 更新一个系统账户
     *
     * @param sysAccount 账户对象
     * @return 受影响的行数
     */
    @PostMapping("/update")
    public Result<Object> update(@RequestBody SysAccount sysAccount) {
        // Ensure the account being updated has the correct ID
        if (sysAccount == null) {
            return Result.error("修改失败", sysAccount);
        }
        int result = sysAccountService.update(sysAccount);
        return Result.ok(result);
    }

    /**
     * 删除一个系统账户
     *
     * @param id 主键 ID
     * @return 受影响的行数
     */
    @GetMapping("/deleteById/{id}")
    public Result<Object> deleteById(@PathVariable Integer id) {
        int result = sysAccountService.deleteById(id);
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
        SysAccount sysAccount = sysAccountService.findById(id);
        if (sysAccount != null) {
            return Result.ok(sysAccount);
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
        List<SysAccount> sysAccounts = sysAccountService.findAll();
        return Result.ok(sysAccounts);
    }
}