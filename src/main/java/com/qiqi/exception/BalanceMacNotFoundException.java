package com.qiqi.exception;

/**
 * 设备mac不存在异常
 */
public class BalanceMacNotFoundException extends Exception {

    public BalanceMacNotFoundException() {
    }

    public BalanceMacNotFoundException(String message) {
        super(message);
    }
}
