package com.qiqi.service.output;

import com.qiqi.model.entity.MsgEntity;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static com.qiqi.constant.InstructionConst.SOCKET_CLOSE_RESPONSE;
import static com.qiqi.constant.BaseConst.START_BYTE;
import static com.qiqi.constant.BaseConst.SERVER_MAC;
import static com.qiqi.constant.BaseConst.RESERVED_DATA;

/**
 * 输出流:重写输出流
 *
 */
public class SimpleChannelOutputBoundHandler extends ChannelOutboundHandlerAdapter{

    private short instruction;
    private static final Logger logger = LoggerFactory.getLogger(SimpleChannelOutputBoundHandler.class);

    /**
     * 将响应返回给客户端
     *
     * @param ctx     上下文
     * @param msg     消息
     * @param promise 权限
     * @throws Exception 处理异常
     */
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        MsgEntity msgEntity = (MsgEntity) msg;
        this.instruction = msgEntity.getInstruction();
        byte[] msgBody = msgEntity.getMsgBody();
        int bodySize = msgBody == null ? 0 : msgBody.length;
        byte[] result = new byte[bodySize + 32];
        //设置数据头(0,1)、数据长度(2,3)、ProductNum(4-7)、服务端(8-13)及客户端mac(14-19)、
        // 保留字节(20,21)、instruction(22,23)共占24
        setBaseResponse(result, msgEntity);
        System.arraycopy(msgEntity.getMsgBody(), 0, result, 24, bodySize);
        //测试用：打印数据到控制台
        logger.info("server发送数据:{}",Arrays.toString(result));
        ctx.write(Unpooled.copiedBuffer(result), promise);
    }

    /**
     * 处理socket关闭请求
     *
     * @param ctx 上下文
     * @throws Exception 处理异常
     */
    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        super.flush(ctx);
        if (instruction == SOCKET_CLOSE_RESPONSE) {
            ctx.close();
        }
    }

    /**
     * 获取基础数据
     *
     * @param data 要补充的数据
     */
    private void setBaseResponse(byte[] data, MsgEntity msgEntity) {
        System.arraycopy(START_BYTE, 0, data, 0, 2); // data[0,1]=85,-86
        setDataLength(data, data.length);  // data[2,3] = data.legth
        System.arraycopy(msgEntity.getProductNum(), 0, data, 4, 4);//data[4,7]
        System.arraycopy(SERVER_MAC, 0, data, 8, 6);//data[8,9,10,11,12,13]
        System.arraycopy(msgEntity.getClientMac(), 0, data, 14, 6);//data[14~19]
        System.arraycopy(RESERVED_DATA, 0, data, 20, 2);//data[20,21]
        setInstructions(data, msgEntity.getInstruction());//data[22,23]
    }


    /**
     * 设置数据长度
     * 解释：为什么要& 0xff 为了保持二进制补码的一致性，计算机存储的是补码
     * 因为byte类型字符是8bit的，而int为32bit 当byte要转化为int的时候，高的24位必然会补1，
     * 这样，其二进制补码其实已经不一致了，&0xff可以将高的24位置为0，低8位保持原样，
     * 这样做的目的就是为了保证二进制数据的一致性。
     * 1111111111111111111111111 10000001&11111111（0xff）=000000000000000000000000 10000001
     * @param data   数据
     * @param length 长度
     */
    private void setDataLength(byte[] data, int length) {
        data[2] = (byte) ((length >>> 8) & 0xff);
        data[3] = (byte) (length & 0xff);
    }


    /**
     * 设置指令
     * @param data 数据
     * @param val  指令
     */
    private void setInstructions(byte[] data, short val) {
        data[22] = (byte) ((val >>> 8) & 0xff);
        data[23] = (byte) (val & 0xff);
    }

}
