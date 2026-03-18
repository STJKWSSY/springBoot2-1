package com.jiangnan.www.springboot02web.demos.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录控制器 - 用于演示拦截器功能
 *
 * @author jiangNan
 */
@Controller
public class LoginController {

    /**
     * 跳转到登录页面
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * 处理登录请求
     */
    @PostMapping("/doLogin")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password) {
        // 简单模拟登录成功（实际项目应该验证数据库）
        if ("admin".equals(username) && "123456".equals(password)) {
            // 将用户信息存入 session
            // 注意：这里需要配合 SpringBoot 的 session 管理
            System.out.println("登录成功：" + username);
            return "redirect:/main";
        }
        return "redirect:/login?error";
    }

    /**
     * 主页（需要登录访问）
     */
    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
