package com.jiangnan.www.springboot01helloword.demos.web.bean;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 汽车
 * 只有在容器中的组件,才会拥有springBoot提供的强大功能
 *
 * @author Jiangnan
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Component
@ConfigurationProperties(prefix = "my-car")
public class Car {

    private String brand;

    private Integer price;

}
