package com.jiangnan.www.springboot02web.demos.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录检查拦截器
 * 演示拦截器的基本用法
 *
 * @author jiangNan
 */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    /**
     * 目标资源方法执行前执行
     * true：放行，调用目标方法
     * false：不放行，中断请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle... 访问路径：" + request.getRequestURI());

        // 模拟登录检查逻辑
        String loginUser = (String) request.getSession().getAttribute("loginUser");

        if (loginUser != null) {
            System.out.println("用户已登录：" + loginUser);
            return true;
        }

        // 未登录，重定向到登录页
        System.out.println("用户未登录，重定向到登录页");
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }

    /**
     * 目标资源方法执行后执行
     * 可用于日志记录、性能监控等
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle... 请求处理完成");

        // 可以在这里修改 ModelAndView
        if (modelAndView != null) {
            modelAndView.addObject("interceptorInfo", "拦截器处理完成");
        }
    }

    /**
     * 整个请求完成后执行（视图渲染后）
     * 可用于资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion... 请求完全结束");
    }
}
