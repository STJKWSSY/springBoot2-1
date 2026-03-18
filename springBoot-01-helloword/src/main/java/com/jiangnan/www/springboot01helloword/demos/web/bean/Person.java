package com.jiangnan.www.springboot01helloword.demos.web.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 人物类
 *
 * @author jiangNan
 */
@ConfigurationProperties(prefix = "person")
@Component
@Data
@ToString
public class Person {

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 是否领导
     */
    private Boolean boss;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 宠物
     */
    private Pet pet;

    /**
     * 兴趣
     */
    private String[] interests;

    /**
     * 动物
     */
    private List<String> animal;

    /**
     * 分数
     */
    private Map<String, Object> score;

    /**
     * 薪资
     */
    private Set<Double> salarys;

    /**
     * 所有的宠物
     */
    private Map<String, List<Pet>> allPets;
}
