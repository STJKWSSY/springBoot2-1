package com.jiangnan.www.springboot03admin.demos.web.config;

import com.jiangnan.www.springboot03admin.demos.web.intercepters.LonginInterceptor;
import com.jiangnan.www.springboot03admin.demos.web.intercepters.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 *
 * @author jiangNan
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    final RedisUrlCountInterceptor redisUrlCountInterceptor;

    public InterceptorConfig(RedisUrlCountInterceptor redisUrlCountInterceptor) {
        this.redisUrlCountInterceptor = redisUrlCountInterceptor;
    }


    /**
     * 登录拦截器
     *
     * @param registry 登记
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LonginInterceptor())
                // 将静态资源文件全部拦截
                .addPathPatterns("/**")
                // 打开 静态资源文件 登录方法
                .excludePathPatterns("/", "/druid", "/login", "/css/**",
                        "/js/**", "/img/**", "/fonts/**",
                        "/images/**", "/lib/**", "/plugins/**", "/aa/**");
        WebMvcConfigurer.super.addInterceptors(registry);
        // redisUrl路径统计拦截器
        registry.addInterceptor(redisUrlCountInterceptor)
                .addPathPatterns("/**")
                // 打开 静态资源文件 登录方法
                .excludePathPatterns("/", "/druid", "/login", "/css/**",
                        "/js/**", "/img/**", "/fonts/**",
                        "/images/**", "/lib/**", "/plugins/**", "/aa/**");
    }


}

