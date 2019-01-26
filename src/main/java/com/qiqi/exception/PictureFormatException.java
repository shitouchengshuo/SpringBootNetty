package com.qiqi.exception;

/**
 * 图片格式异常
 */
public class PictureFormatException extends Exception {
    public PictureFormatException() {
    }

    public PictureFormatException(String message) {
        super(message);
    }

    public PictureFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
