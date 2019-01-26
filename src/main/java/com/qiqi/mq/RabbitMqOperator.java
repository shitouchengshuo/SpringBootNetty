package com.qiqi.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 消息队列，存储体重信息
 */
@Component
public class RabbitMqOperator {

    private AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitMqOperator(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    /**
     * 发送消息到rabbitmq
     * @param data
     */
    @Async(value = "customThreadPoolExecutor")
    public void push(byte[] data){
        amqpTemplate.convertAndSend(data);
    }
}
