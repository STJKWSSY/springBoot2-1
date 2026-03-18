package com.jiangnan.www.springboot03admin.demos.web.controller;

import com.jiangnan.www.springboot03admin.demos.web.exception.MmdBusinessException;
import com.jiangnan.www.springboot03admin.demos.web.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 错误处理测试类
 *
 * @author jiangNan
 */
@RestController
@RequestMapping("/exceptionTest")
public class ExceptionTestController {


    /**
     * {@code @RestControllerAdvice} & @ExceptionHandler 测试
     * <p>
     * 错误信息处理器 {@link com.jiangnan.www.springboot03admin.demos.web.exception.GlobalExceptionHandler}
     * 自定义错误类 {@link MmdBusinessException}
     */
    @GetMapping("/customExceptionTest")
    public Result<String> customExceptionTest() {
        throw new MmdBusinessException("123123");
    }



}
