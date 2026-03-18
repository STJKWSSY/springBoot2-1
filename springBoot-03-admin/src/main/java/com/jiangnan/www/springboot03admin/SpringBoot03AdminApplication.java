package com.jiangnan.www.springboot03admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ServletComponentScan("com.jiangnan.www.springboot03admin")
@SpringBootApplication
@EnableWebMvc
public class SpringBoot03AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot03AdminApplication.class, args);
    }

}
