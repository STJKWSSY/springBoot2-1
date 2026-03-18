package com.jiangnan.www.springboot03admin.demos.web.exception;

import com.jiangnan.www.springboot03admin.demos.web.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author jiangNam
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * MMd商业错误
     *
     * @return Result结果
     */
    @ExceptionHandler(MmdBusinessException.class)
    public Result<Object> handleArityException(){
        return Result.error("MMD商业模式错误",null);
    }

    /**
     * 拦截器错误
     */
    @ExceptionHandler(InterceptedException.class)
    public Result<Object> interceptorException(){
        return Result.error("拦截器错误",null);
    }


}
