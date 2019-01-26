package com.qiqi.exception;

/**
 * 数据格式不对
 */
public class DataFormatException extends Exception {

    public DataFormatException() {
    }

    public DataFormatException(String message) {
        super(message);
    }

    public DataFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
