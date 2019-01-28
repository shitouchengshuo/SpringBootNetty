package com.qiqi.mq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 消息队列
 */
@Component
public class RabbitMqOperator {

    private AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitMqOperator(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    /**
     * 异步发送字节数组到rabbitmq
     * @param data
     */
    @Async(value = "customThreadPoolExecutor")
    public void push(String routingKey, byte[] data){
        amqpTemplate.convertAndSend(routingKey, data);
    }


    /**
     * 异步发送int类型消息到rabbitmq
     * @param data
     */
    @Async(value = "customThreadPoolExecutor")
    public void pushInt(String routingKey, int data){
        amqpTemplate.convertAndSend(routingKey, data);
    }

    /**
     * 定义一个名为：first_queue 的队列
     * @return
     */
    @Bean
    public Queue firstQueue() {
        return new Queue("first_queue");
    }

    /**
     * 定义一个名为：second_queue 的队列
     * @return
     */
    @Bean
    public Queue secondQueue() {
        return new Queue("second_queue");
    }

    //--------------使用Fanout Exchange，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息------------------
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(Queue firstQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(firstQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue secondQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(secondQueue).to(fanoutExchange);
    }
    /**
     * 异步发送int类型消息到rabbitmq
     * @param data
     */
    @Async(value = "customThreadPoolExecutor")
    public void pushIntFanout(String exchange, String routingKey, int data){
        amqpTemplate.convertAndSend(exchange, routingKey, data);
    }
    //---------------使用Topic Exchange，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息------------------
}
