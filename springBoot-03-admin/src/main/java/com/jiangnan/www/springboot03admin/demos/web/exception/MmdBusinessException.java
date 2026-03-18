package com.jiangnan.www.springboot03admin.demos.web.exception;


/**
 * 自定义mmd商业错误类
 *
 * @author jiangNan
 */
public class MmdBusinessException extends RuntimeException {

    /**
     * mmd商业错误类
     *
     * @param message 错误信息
     */
    public MmdBusinessException(String message) {
        super(message);
    }

    /**
     * mmd商业错误类
     *
     * @param message 错误信息
     * @param cause   错误类原因
     */
    public MmdBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * mmd错误类
     *
     * @param cause 错误原因
     */
    public MmdBusinessException(Throwable cause) {
        super(cause);
    }


}
