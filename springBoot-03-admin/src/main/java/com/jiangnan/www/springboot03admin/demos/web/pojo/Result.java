package com.jiangnan.www.springboot03admin.demos.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * restful接口返回结果类
 *
 *
 * @author jiangNan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    /**
     * code
     */
    private int code;


    /**
     * message
     */
    private String message;


    /**
     * data
     */
    private T data;

    /**
     * 设置成功方法
     */
    public  Result<T> setSuccess(String message) {
        return new Result<>(200, message, null);
    }


    /**
     * 设置error方法
     */
    public  Result<T> setError(String message) {
        return new Result<>(500, message, null);
    }

    /**
     * ok
     */
    public static  Result<Object> ok(Object data) {
        return new Result<>(200, "success", data);
    }

    /**
     * error
     */
    public static  Result<Object> error(String message, Object data) {
        return new Result<>(400, "error", data);
    }

}
