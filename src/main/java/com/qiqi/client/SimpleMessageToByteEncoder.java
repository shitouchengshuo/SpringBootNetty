package com.qiqi.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 */
public class SimpleMessageToByteEncoder extends MessageToByteEncoder<byte[]> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, byte[] msg, ByteBuf out) throws Exception {
        //编码器，将byte[]值编码成ByteBuf
        out.writeBytes(msg);
    }
}
