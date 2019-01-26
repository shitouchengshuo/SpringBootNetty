package com.qiqi.service.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * Netty 前置解码器 将字节流转换为字节数组
 */
public class SimpleByteToMessageDecoder extends ByteToMessageDecoder {

    /**
     * 最小协议长度
     */
    private static final int MIN_LEN = 14;


    /**
     * 读取数据
     *
     * @param channelHandlerContext 上下文
     * @param byteBuf               输入的byteBuf
     * @param list                  字符流
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        if (byteBuf.readableBytes() < MIN_LEN) {
            return;
        }
        //将当前的readerIndex备份到markedReaderIndex中
        byteBuf.markReaderIndex();

        byte[] heads = new byte[4];
        //将当前ByteBuf中的数据读取到byte数组heads中,从当前ByteBuf readerIndex开始读取，
        // 读取长度为heads.length，从byte数组dst索引0处开始写入数据。读取完成后，当前ByteBuf的readerIndex+=length
        byteBuf.readBytes(heads);
        //将当前的readerIndex重置为markedReaderIndex的值
        byteBuf.resetReaderIndex();
        byte[] messages = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(messages);
        list.add(messages);
    }
}
