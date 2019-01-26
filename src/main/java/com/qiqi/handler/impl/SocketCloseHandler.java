package com.qiqi.handler.impl;

import com.qiqi.annotation.Handler;
import com.qiqi.model.entity.MsgEntity;
import io.netty.channel.ChannelHandlerContext;
import static com.qiqi.constant.InstructionConst.SOCKET_CLOSE_REQUEST;
import static com.qiqi.constant.InstructionConst.SOCKET_CLOSE_RESPONSE;

/**
 * 关闭连接
 */
@Handler(instruction = SOCKET_CLOSE_REQUEST)
public class SocketCloseHandler extends AbstractMessageHandler {

    /**
     * 客户端要求关闭连接
     *
     * @param ctx     上下文
     * @param message 待处理的消息
     * @return 连接关闭响应
     */
    @Override
    public MsgEntity doDecoder(ChannelHandlerContext ctx, byte[] message) {
        MsgEntity msgEntity = register(message);
        msgEntity.setInstruction(SOCKET_CLOSE_RESPONSE);
        msgEntity.setMsgBody(new byte[0]);
        return msgEntity;
    }
}
