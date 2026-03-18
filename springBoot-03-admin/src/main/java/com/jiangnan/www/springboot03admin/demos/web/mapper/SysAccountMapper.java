package com.jiangnan.www.springboot03admin.demos.web.mapper;

import com.jiangnan.www.springboot03admin.demos.web.pojo.SysAccount;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 系统账户服务
 *
 * @author jiangNan
 */
@Mapper
public interface SysAccountMapper {


    /**
     * 插入一条记录
     *
     * @param sysAccount 账户对象
     * @return 受影响的行数
     */
    @Insert("INSERT INTO sys_account_demo (name, age) VALUES (#{name}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SysAccount sysAccount);

    /**
     * 更新一条记录
     *
     * @param sysAccount 账户对象
     * @return 受影响的行数
     */
    @Update("UPDATE sys_account_demo SET name = #{name}, age = #{age} WHERE id = #{id}")
    int update(SysAccount sysAccount);

    /**
     * 删除一条记录
     *
     * @param id 主键 ID
     * @return 受影响的行数
     */
    @Delete("DELETE FROM sys_account_demo WHERE id = #{id}")
    int deleteById(Integer id);

    /**
     * 根据主键 ID 查询一条记录
     *
     * @param id 主键 ID
     * @return 账户对象
     */
    @Select("SELECT * FROM sys_account_demo WHERE id = #{id}")
    SysAccount findById(Integer id);

    /**
     * 查询所有记录
     *
     * @return 账户对象列表
     */
    @Select("SELECT * FROM sys_account_demo")
    List<SysAccount> findAll();

}
