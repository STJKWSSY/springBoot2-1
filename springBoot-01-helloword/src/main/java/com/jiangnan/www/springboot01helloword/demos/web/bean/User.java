package com.jiangnan.www.springboot01helloword.demos.web.bean;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {

    /**
     * 用户名称
     */
    private  String name;


    /**
     * 用户年龄
     */
    private  int age;

    /**
     * 用户地址
     */
    private  String address;

    /**
     * 宠物
     */
    private  Pet pet;
}
