package com.qiqi.exception;

/**
 * 时间格式异常
 */
public class TimeFormatException extends Exception {

    public TimeFormatException() {
    }

    public TimeFormatException(String message) {
        super(message);
    }

    public TimeFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
