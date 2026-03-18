package com.jiangnan.www.springboot03admin.demos.web.exception;

/**
 * 处理拦截器错误
 *
 * @author jiangNan
 */
public class InterceptedException extends RuntimeException{
    public InterceptedException() {
        super();
    }

    public InterceptedException(String message) {
        super(message);
    }

    public InterceptedException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterceptedException(Throwable cause) {
        super(cause);
    }
}
