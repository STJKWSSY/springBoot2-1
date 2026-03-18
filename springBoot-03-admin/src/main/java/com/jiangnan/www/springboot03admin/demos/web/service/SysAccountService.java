package com.jiangnan.www.springboot03admin.demos.web.service;

import com.jiangnan.www.springboot03admin.demos.web.pojo.SysAccount;

import java.util.List;

/**
 * 系统账户服务接口
 *
 * @author jiangNan
 */
public interface SysAccountService {

    /**
     * 插入一条记录
     *
     * @param sysAccount 账户对象
     * @return 受影响的行数
     */
    int insert(SysAccount sysAccount);

    /**
     * 更新一条记录
     *
     * @param sysAccount 账户对象
     * @return 受影响的行数
     */
    int update(SysAccount sysAccount);

    /**
     * 删除一条记录
     *
     * @param id 主键 ID
     * @return 受影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据主键 ID 查询一条记录
     *
     * @param id 主键 ID
     * @return 账户对象
     */
    SysAccount findById(Integer id);

    /**
     * 查询所有记录
     *
     * @return 账户对象列表
     */
    List<SysAccount> findAll();
}