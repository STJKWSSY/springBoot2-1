package com.jiangnan.www.springboot02web.demos.web.controller;

import cn.hutool.core.map.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 矩阵变量测试
 *
 * @author jiangNan
 */
@RequestMapping("/matrixVariable")
@Controller
public class MatrixVariableController {


    /**
     * {@code @MatrixVariable} 矩阵变量 通过矩阵变量获取汽车信息
     *
     * <see> springBoot默认禁止了MatrixVariable功能,所以我们需要手动开启MatrixVariable功能 </see>
     * {@link com.jiangnan.www.springboot02web.demos.web.config.MatrixVariableConfig}
     */
    @GetMapping("/testMatrixVariable")
    public Map<String, Object> testMatrixVariable(@MatrixVariable("color") String color,
                                      @MatrixVariable("age") Integer age,
                                      @MatrixVariable("brand") List<String> brand) {
        return MapUtil.builder(new java.util.HashMap<String, Object>())
                .put("color", color)
                .put("age", age)
                .put("brand", brand)
                .build();
    }
}
