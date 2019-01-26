package com.qiqi.service.input;

import com.qiqi.exception.BalanceMacNotFoundException;
import com.qiqi.handler.MessageHandler;
import com.qiqi.model.entity.MsgEntity;
import com.qiqi.service.SpringHandlerManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Netty处理读写事件
 */
public class SimpleChannelInputBoundHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SimpleChannelInputBoundHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //前置解码器SimpleByteToMessageDecoder已经将字节流转换为字节数组
        byte[] inputMsg = (byte[]) msg;
        //测试用：打印数据到控制台
        logger.info("server接收数据:{}",Arrays.toString(inputMsg));
        short instruction = inputMsg[0];
        MessageHandler messageHandler = SpringHandlerManager.getMessageHandler(instruction);
        if (messageHandler == null) {
            throw new Error("Protocol illegal.");
        }
        MsgEntity msgEntity = messageHandler.doDecoder(ctx, inputMsg);
        //这里会调用SimpleChannelOutputBoundHandler的write()
        ctx.writeAndFlush(msgEntity);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause instanceof BalanceMacNotFoundException) {
            logger.info("mac : {} might is factory test device.", cause.getMessage());
        } else {
            logger.info(cause.getMessage(), cause);
        }
        ctx.close();
    }


}
