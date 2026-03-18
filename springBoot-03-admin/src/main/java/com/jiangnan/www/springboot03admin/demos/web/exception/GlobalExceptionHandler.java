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
    public String handleArityException(){
//        return  Result.error("MMD商业模式错误");
        return "MMD商业模式错误";
    }

    /**
     * 拦截器错误
     */
    @ExceptionHandler(InterceptedException.class)
    public String interceptorException(){
//        return  Result.error("MMD商业模式错误");
        return "拦截器错误";
    }


}
