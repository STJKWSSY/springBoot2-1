package com.jiangnan.www.springboot03admin.demos.web.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@code @ResponseStatus} 异常
 *
 * @author jiangNan
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "请求参数错误")
public class ResponseStatusException extends RuntimeException {


    public ResponseStatusException(String message) {
        super(message);
    }

    public ResponseStatusException(Throwable cause) {
        super(cause);
    }
}
