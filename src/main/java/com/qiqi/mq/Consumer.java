package com.qiqi.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    /**
     * @RabbitHandler 指定消息的处理方法
     * @param message
     */
    @RabbitListener(queues = "qiqi_queue")////@RabbitListener 监听 qiqi_queue 队列
    @RabbitHandler
    public void process(String message) {
        log.info("接收的消息为: {}", message);
    }
}
