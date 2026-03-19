package com.jiangnan.www.springboot03admin.demos.web.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_demo")
public class User implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String  userName;

    /**
     * 用户年龄
     */
    @TableField(value = "age")
    private Integer age;


}
