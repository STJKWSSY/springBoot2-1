package com.jiangnan.www.springboot01helloword.demos.web.config;

import com.jiangnan.www.springboot01helloword.demos.web.bean.Dog;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * dog属性配置类

 * {@code @EnableConfigurationProperties}  1.开启Car配置绑定功能 2.自动注册到容器中 ,这里就不需要将对象注册到spring容器中 @Component不需要了
 * 先将DogConfig类注册到容器中
 */
@Configuration
@EnableConfigurationProperties(Dog.class)
public class DogConfig {

}
