package com.qiqi.mq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
     * 异步发送消息到rabbitmq
     * @param data
     */
    @Async(value = "customThreadPoolExecutor")
    public void push(String queue, byte[] data){
        amqpTemplate.convertAndSend(queue, data);
    }

    /**
     * 定义一个名为：qiqi_queue 的队列
     * @return
     */
    @Bean
    public Queue queueQueue() {
        return new Queue("qiqi_queue");

    }
}
