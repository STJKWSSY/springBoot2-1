package com.jiangnan.www.springboot03admin.demos.web.service.impl;

import com.jiangnan.www.springboot03admin.demos.web.mapper.SysAccountMapper;
import com.jiangnan.www.springboot03admin.demos.web.pojo.SysAccount;
import com.jiangnan.www.springboot03admin.demos.web.service.SysAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统账户服务实现类
 *
 * @author jiangNan
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysAccountServiceImpl implements SysAccountService {

    private final SysAccountMapper sysAccountMapper;

    public SysAccountServiceImpl(SysAccountMapper sysAccountMapper) {
        this.sysAccountMapper = sysAccountMapper;
    }

    /**
     * 插入一条记录
     *
     * @param sysAccount 账户对象
     * @return 受影响的行数
     */
    @Override
    public int insert(SysAccount sysAccount) {
        return sysAccountMapper.insert(sysAccount);
    }

    /**
     * 更新一条记录
     *
     * @param sysAccount 账户对象
     * @return 受影响的行数
     */
    @Override
    public int update(SysAccount sysAccount) {
        return sysAccountMapper.update(sysAccount);
    }

    /**
     * 删除一条记录
     *
     * @param id 主键 ID
     * @return 受影响的行数
     */
    @Override
    public int deleteById(Integer id) {
        return sysAccountMapper.deleteById(id);
    }

    /**
     * 根据主键 ID 查询一条记录
     *
     * @param id 主键 ID
     * @return 账户对象
     */
    @Override
    public SysAccount findById(Integer id) {
        return sysAccountMapper.findById(id);
    }

    /**
     * 查询所有记录
     *
     * @return 账户对象列表
     */
    @Override
    public List<SysAccount> findAll() {
        return sysAccountMapper.findAll();
    }
}