package com.qiqi.exception;

/**
 * 数据不存在
 */
public class DataNotExistException extends Exception {

    public DataNotExistException() {
    }

    public DataNotExistException(String message) {
        super(message);
    }

    public DataNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
