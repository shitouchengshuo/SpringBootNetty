package com.qiqi.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class ClientHandler extends ChannelInboundHandlerAdapter{
    private final ByteBuf firstMSG;
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    public ClientHandler() {
        byte[] req = new byte[]{3,0,0,0,0,0,2,3,11,11,11,11,11,11,70};
        firstMSG = Unpooled.buffer(req.length);
        firstMSG.writeBytes(req);
        logger.info("客户端发送数据:{}", Arrays.toString(req));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMSG);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        logger.info("客户端接收数据:{}", Arrays.toString(req));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
