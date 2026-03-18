package com.jiangnan.www.springboot01helloword.demos.web.bean;

import lombok.*;

/**
 * 宠物
 *
 * @author grolia
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Pet {

    /**
     * 宠物名称
     */
    private String name;

    /**
     * 宠物体重
     */
    private Integer weight;


}
