package com.jiangnan.www.springboot03admin.demos.web.servlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "myServlet", urlPatterns = "/exceptionTest")
public class MyServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        resp.getWriter().write("这是一个自定义的Servlet");
    }
}
