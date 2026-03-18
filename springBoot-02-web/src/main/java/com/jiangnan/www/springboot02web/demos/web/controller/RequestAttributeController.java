package com.jiangnan.www.springboot02web.demos.web.controller;

import cn.hutool.core.map.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * {@code @RequestAttribute}  获取request域属性
 *
 * @author jiangNan
 */
@Controller
@RequestMapping("/requestAttribute")
public class RequestAttributeController {


    /**
     * `@RequestAttribute` 获取request域属性
     */
    @GetMapping("/testRequestAttribute")
    public String testRequestAttribute(HttpServletRequest request) {
        request.setAttribute("msg", "success");
        request.setAttribute("code", 200);
        return "forward:/requestAttribute/getRequestAttribute";
    }


    /**
     * `@RequestAttribute` 获取request域属性
     *
     * @param msg     通过@RequestAttribute注解获取request域属性msg
     * @param code    通过@RequestAttribute注解获取request域属性code
     * @param request HttpServletRequest对象
     * @return Map<String, Object>  返回request域属性msg和code，以及通过HttpServletRequest对象获取的request域属性msg和code
     */
    @ResponseBody
    @GetMapping("/getRequestAttribute")
    public Map<String, Object> getRequestAttribute(@RequestAttribute("msg") String msg, @RequestAttribute("code") Integer code, HttpServletRequest request) {
        // 通过HttpServletRequest对象获取request域属性
        Object reqMes = request.getAttribute("msg");
        Object reqCode = request.getAttribute("code");
        return MapUtil.builder(new java.util.HashMap<String, Object>())
                .put("msg", msg)
                .put("code", code)
                .put("reqMsg", reqMes)
                .put("reqCode", reqCode)
                .build();

    }
}
