package com.qiqi.handler.impl;

import com.qiqi.annotation.Handler;
import com.qiqi.exception.ProtocolHandlerException;
import com.qiqi.handler.WeightHandler;
import com.qiqi.model.entity.MsgEntity;
import io.netty.channel.ChannelHandlerContext;

import static com.qiqi.constant.InstructionConst.WEIGHT_UPLOAD_Q1_REQUEST;
import static com.qiqi.constant.InstructionConst.WEIGHT_UPLOAD_Q1_RESPONSE;

/**
 * 产品Q1数据处理
 */
@Handler(instruction = WEIGHT_UPLOAD_Q1_REQUEST )
public class Q1Handler extends AbstractMessageHandler implements WeightHandler {

    private static final int WEIGHT_UPLOAD_TYPE_LENGTH = 100;
    //消息队列
    //private RabbitMqOperator rabbitMqOperator;

    // @Autowired
    // public Q1Handler(RabbitMqOperator operator) {
    //     this.rabbitMqOperator = operator;
    //     Assert.notNull(this.rabbitMqOperator, "rabbitmq init fail.");
    // }

    /**
     * 解析设备上报数据
     *
     * @param ctx     上下文
     * @param message 待处理的消息
     * @return 响应信息
     */
    @Override
    public MsgEntity doDecoder(ChannelHandlerContext ctx, byte[] message) throws ProtocolHandlerException {
        MsgEntity msgEntity = register(message); //设置productNumber(4-7)、clientMac(8-13)
        weightHandler(message, msgEntity);
        byte[] msgBody = new byte[8];
        setSystemTime(msgBody);  //时间占msgBody的前7个字节
        // printer.info("product : {} , mac : {} , S7 single frequency V25", product, mac);
        msgEntity.setMsgBody(msgBody);
        msgEntity.setInstruction(WEIGHT_UPLOAD_Q1_RESPONSE);
        return msgEntity;
    }

    /**
     * 体重解析，将解析后的体重数据发送到rabbitMq
     *
     * @param message   原始信息
     * @param msgEntity 解析过的一部分信息
     */
    @Override
    public void weightHandler(byte[] message, MsgEntity msgEntity) {
        //byte[]message  0~3: head; 4~7:productNumber;  8~13:clientMac; 14:weight
        //byte[]data  0~3:productNumber; 4~9:clientMac;  10:weight
        byte[] data = new byte[12];

        //产品编码
        System.arraycopy(msgEntity.getProductNum(), 0, data, 0, 4);
        // mac
        System.arraycopy(msgEntity.getClientMac(), 0, data, 4, 6);
        // weight
        data[10] = message[14];

        //rabbitMqOperator.push(data);
    }
}
