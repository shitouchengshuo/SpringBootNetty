package com.qiqi.handler;

import com.qiqi.exception.ProtocolHandlerException;
import com.qiqi.model.entity.MsgEntity;

/**
 * 解析体重测量结果
 */
public interface WeightHandler {
    /**
     *
     * @param message   原始的信息
     * @param msgEntity 解析过的一部分信息
     * @throws ProtocolHandlerException 协议处理失败
     */
    void weightHandler(byte[] message, MsgEntity msgEntity)throws ProtocolHandlerException;
}
