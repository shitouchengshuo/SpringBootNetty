package com.qiqi.exception;

/**
 * 签名错误
 */
public class WrongSignatureException extends Exception {

    public WrongSignatureException() {
    }

    public WrongSignatureException(String message) {
        super(message);
    }

    public WrongSignatureException(String message, Throwable cause) {
        super(message, cause);
    }
}
