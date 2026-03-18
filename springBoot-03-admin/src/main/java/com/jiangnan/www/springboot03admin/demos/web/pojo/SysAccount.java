package com.jiangnan.www.springboot03admin.demos.web.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 系统账户类
 *
 * @author jiangNan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAccount {

    /**
     * 主键 id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

}
