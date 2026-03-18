package com.jiangnan.www.springboot02web.demos.web.config;

import com.jiangnan.www.springboot02web.demos.web.interceptor.LoginCheckInterceptor;
import com.jiangnan.www.springboot02web.demos.web.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 配置类 - 配置拦截器
 *
 * @author jiangNan
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加登录检查拦截器
        registry.addInterceptor(loginCheckInterceptor)
                // 拦截所有路径
                .addPathPatterns("/**")
                // 排除登录、登出接口（避免循环重定向）
                .excludePathPatterns(
                        "/login",
                        "/doLogin",
                        "/",
                        "/index.html",
                        "/res/**",
                        "/static/**",
                        "/*.ico",
                        "/*.png",
                        "/*.jpg"
                );
    }

    /**
     * 重写Convert,实现自定义的
     */
    @Autowired

    @Override
    public void addFormatters(FormatterRegistry registry) {
       // Sting cast pet
        registry.addConverter(new Converter<String, User>() {

            @Nullable
            @Override
            public User convert(String source) {
                if(!StringUtils.isEmpty(source)){
                    User user = new User();
                    String[] splitParam = source.split("-");
                    user.setName(splitParam[0]);
                    user.setAge(Integer.valueOf(splitParam[1]));
                }
                return null;
            }
        });
    }
}
