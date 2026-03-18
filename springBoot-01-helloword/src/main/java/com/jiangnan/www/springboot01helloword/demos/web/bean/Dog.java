package com.jiangnan.www.springboot01helloword.demos.web.bean;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Dog
 *
 * @author jiangNan
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@ConfigurationProperties(prefix = "my-dog")
public class Dog {

    private String name;

    private Integer age;
}
