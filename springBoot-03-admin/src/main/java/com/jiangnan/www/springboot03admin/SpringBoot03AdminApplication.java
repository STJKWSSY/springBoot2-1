package com.jiangnan.www.springboot03admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ServletComponentScan("com.jiangnan.www.springboot03admin")
@SpringBootApplication
@MapperScan("com.jiangnan.www.springboot03admin.demos.web.mapper")
public class SpringBoot03AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot03AdminApplication.class, args);
    }

}
