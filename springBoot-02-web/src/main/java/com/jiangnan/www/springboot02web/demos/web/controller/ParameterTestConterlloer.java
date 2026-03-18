package com.jiangnan.www.springboot02web.demos.web.controller;


import cn.hutool.core.map.MapUtil;
import com.jiangnan.www.springboot02web.demos.web.pojo.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 常用请求参数注解使用测试
 *
 * @author jiangNan
 */
@RequestMapping("paramTest")
@RestController
public class ParameterTestConterlloer {


    /**
     * `@PathVariable` 路径变量 通过carId获取car信息
     *
     * @param carId 汽车ID
     * @param name  汽车名称
     * @param pv    通过@PathVariable注解获取所有路径变量
     * @return Map<String, Object> 汽车信息
     */
    @GetMapping("/car/{carId}/name/{name}")
    public Map<String, Object> getCar(@PathVariable Integer carId, @PathVariable String name,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("user-agent") String userAgent,
                                      @RequestHeader Map<String, String> header,
                                      @RequestParam Integer age,
                                      @RequestParam List<String> inters
    ) {
        return MapUtil.builder(new HashMap<String, Object>())
                .put("carId", carId)
                .put("name", name)
                .put("pv", pv)
                .put("userAgent", userAgent)
                .put("header", header)
                .put("age", age)
                .put("inters", inters)
                .build();
    }

    /**
     * `@RequestHeader` 获取请求头信息（指浏览器发送的请求头信息，包含User-Agent、Accept等）,获取请求头信息（指浏览器发送的请求头信息，包含User-Agent、Accept等）
     *
     * <p>
     *    <li>可以单独获取head中的某个信息,也可以通过Map<String,String>的方式获取全部</li>
     * </p>
     */
    @GetMapping("requestHeaderTest")
    public Map<String, Object> requestHeaderTest(@RequestHeader("User-Agent") String userAgent,
                                                 @RequestHeader Map<String, String> header) {
        return MapUtil.builder(new HashMap<String, Object>())
                .put("userAgent", userAgent)
                .put("header", header)
                .build();
    }

    /**
     * {@code @RequestParam} 获取请求参数（指问号后的参数，url?a=1&b=2）,获取请求参数（指问号后的参数，url?a=1&b=2）
     *
     * @param age    年龄
     * @param inters 兴趣爱好列表
     * @return Map<String,Object> 请求参数信息
     */
    @GetMapping("/requestParamTest")
    public Map<String, Object> requestParamTest(@RequestParam Integer age, @RequestParam List<String> inters) {
        return MapUtil.builder(new HashMap<String, Object>())
                .put("age", age)
                .put("inters", inters)
                .build();
    }

    /**
     * {@code @CookieValue} 获取Cookie信息（指浏览器发送的Cookie信息）
     *
     * @param _ga   通过@CookieValue注解获取指定Cookie信息
     * @param cookies 通过@CookieValue注解获取所有Cookie信息
     * @return 返回Cookie信息
     */
    @GetMapping("/cookieValueTest")
    public Map<String, Object> cookieValueTest(@CookieValue("_ga") String _ga,
                                               @CookieValue ("_ga")Cookie cookies) {
        return MapUtil.builder(new HashMap<String, Object>())
                .put("_ga", _ga)
                .put("cookies", cookies)
                .build();
    }

    /**
     * `@RequestBody` 获取请求体信息（指POST请求中的请求体信息，通常是JSON格式的数据）
     */
    @PostMapping("/requestBodyTest")
    public String requestBodyTest(@RequestBody User user) {
        return user.toString();
    }

    /**
     * @RequestAttribute 获取请求属性信息（指在请求处理过程中设置的属性信息，通常是通过@RequestAttribute注解设置的属性信息）
     */
}
