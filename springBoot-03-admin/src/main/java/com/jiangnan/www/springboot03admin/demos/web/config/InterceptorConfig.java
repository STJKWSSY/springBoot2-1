package com.jiangnan.www.springboot03admin.demos.web.config;

import com.jiangnan.www.springboot03admin.demos.web.intercepters.LonginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 *
 * @author jiangNan
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LonginInterceptor())
                // 将静态资源文件全部拦截
                .addPathPatterns("/**")
                // 打开 静态资源文件 登录方法
                .excludePathPatterns("/", "/login", "/css/**",
                        "/js/**", "/img/**", "/fonts/**",
                        "/images/**", "/lib/**", "/plugins/**", "/aa/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

