package com.qiqi.exception;

/**
 * 用户无效
 */
public class UserValidationException extends Exception {

    public UserValidationException() {
    }

    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
