package com.qiqi.handler.impl;

import com.qiqi.annotation.Handler;
import com.qiqi.exception.ProtocolHandlerException;
import com.qiqi.handler.WeightHandler;
import com.qiqi.model.entity.MsgEntity;
import com.qiqi.mq.RabbitMqOperator;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import static com.qiqi.constant.InstructionConst.WEIGHT_UPLOAD_Q1_REQUEST;
import static com.qiqi.constant.InstructionConst.WEIGHT_UPLOAD_Q1_RESPONSE;

/**
 * 产品Q1数据处理
 */
@Handler(instruction = WEIGHT_UPLOAD_Q1_REQUEST )
public class Q1Handler extends AbstractMessageHandler implements WeightHandler {

    private static final int WEIGHT_UPLOAD_TYPE_LENGTH = 100;

    //同步log
    private static final Logger logger = LogManager.getLogger(Q1Handler.class);
    //消息队列
    private RabbitMqOperator rabbitMqOperator;

    @Autowired
    public Q1Handler(RabbitMqOperator operator) {
        this.rabbitMqOperator = operator;
        Assert.notNull(this.rabbitMqOperator, "rabbitmq init fail.");
    }

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
        String product = msgEntity.getProduct();
        String mac = msgEntity.getMac();
        weightHandler(message, msgEntity);
        byte[] msgBody = new byte[8];
        setSystemTime(msgBody);  //时间占msgBody的前7个字节
        printer.info("异步打印：product : {} , mac : {} , Q1 Handler", product, mac);
        logger.info("同步打印：product : {} , mac : {} , Q1 Handler", product, mac);
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

        byte[] data = new byte[11];
        System.arraycopy(msgEntity.getProductNum(), 0, data, 0, 4);
        System.arraycopy(msgEntity.getClientMac(), 0, data, 4, 6);
        data[10] = message[14];
        rabbitMqOperator.push("first_queue" ,data);

        //测试rabbitMq Direct Exchange模式
        // for (int i = 0; i < 100; i++){
        //     rabbitMqOperator.pushInt("second_queue",i);
        // }

        //测试Fanout Exchange模式
        for (int i = 0; i < 100; i++){
            //这里使用了两个队列绑定到Fanout交换机上面，发送端的routing_key写任何字符都会被忽略
            //监听两个队列的Consumer均会收到相同的一份消息
        rabbitMqOperator.pushIntFanout("fanoutExchange","",i);
        }
    }
}
