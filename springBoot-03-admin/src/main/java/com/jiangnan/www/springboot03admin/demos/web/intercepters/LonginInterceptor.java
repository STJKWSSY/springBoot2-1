package com.jiangnan.www.springboot03admin.demos.web.intercepters;

import com.jiangnan.www.springboot03admin.demos.web.exception.InterceptedException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LonginInterceptor implements HandlerInterceptor {

    /**
     * 目标执行前方法
     *
     * @param request  请求
     * @param response 响应
     * @param handler  控制器
     * @return 执行结构
     * @throws Exception 错误
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String requestURI = request.getRequestURI();
            System.out.println("进入拦截器 requestURI = " + requestURI);
            // 这里处理业务
            return true;
        } catch (Exception e) {
           throw new InterceptedException(this.getClass().getSimpleName() + "preHandle");
        }
    }


    /**
     * 目标执行之后方法
     *
     * @param request 请求
     * @param response 响应
     * @param handler 控制器
     * @param modelAndView 模型和视图
     * @throws Exception 错误
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        try {
            System.out.println("postHandle 执行了" + modelAndView);
        } catch (Exception e) {
            throw new InterceptedException(this.getClass().getSimpleName() + "postHandle");
        }

    }

    /**
     * 页面渲染之后
     *
     * @param request 请求
     * @param response 响应
     * @param handler 控制器
     * @param ex 错误
     * @throws Exception 错误
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

        try {
            // 执行具体业务
            System.out.println("执行 afterCompletion");
        } catch (Exception e) {
            throw new InterceptedException(this.getClass().getSimpleName() + "afterCompletion");
        }
    }
}
