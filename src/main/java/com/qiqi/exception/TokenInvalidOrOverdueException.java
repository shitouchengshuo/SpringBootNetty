package com.qiqi.exception;

/**
 * token无效或者过期
 */
public class TokenInvalidOrOverdueException extends Exception {
    public TokenInvalidOrOverdueException() {
    }

    public TokenInvalidOrOverdueException(String message) {
        super(message);
    }

    public TokenInvalidOrOverdueException(String message, Throwable cause) {
        super(message, cause);
    }
}
