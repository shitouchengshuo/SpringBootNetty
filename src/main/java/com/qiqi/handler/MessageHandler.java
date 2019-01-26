package com.qiqi.handler;

import com.qiqi.exception.ProtocolHandlerException;
import com.qiqi.model.entity.MsgEntity;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息处理接口
 */
public interface MessageHandler  {
    /**
     * 消息处理
     *
     * @param message 待处理的消息
     * @param ctx     上下文
     * @return 有效的消息内容
     * @throws ProtocolHandlerException    协议处理异常
     */
    MsgEntity doDecoder(ChannelHandlerContext ctx, byte[] message) throws ProtocolHandlerException;
}
