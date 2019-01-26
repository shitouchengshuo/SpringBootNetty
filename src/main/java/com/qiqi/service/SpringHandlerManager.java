package com.qiqi.service;

import com.google.common.collect.Maps;
import com.qiqi.annotation.Handler;
import com.qiqi.handler.MessageHandler;
import org.springframework.context.ApplicationContext;
import java.util.Map;

/**
 * spring 上下文
 */
public class SpringHandlerManager {
    private static ApplicationContext applicationContext;

    private static Map<Short, MessageHandler> handlerMap = Maps.newHashMap();

    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 注册服务  在启动类中调用本方法，将处理类添加到map中,instruction作为key
     *
     * @param applicationContext 上下文
     */
    public static void register(ApplicationContext applicationContext) {
        SpringHandlerManager.applicationContext = applicationContext;
        annotationRegister();
    }

    /**
     * 注册handler  通过注解获取instruction作为key,将handler添加到map
     */
    private static void annotationRegister() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Handler.class);
        beans.forEach((s, handlerBean) -> {
            if (!(handlerBean instanceof MessageHandler)) {
                throw new Error("@Handler should only be used on MessageHandler");
            }
            MessageHandler messageHandler = (MessageHandler) handlerBean;
            Handler handler = handlerBean.getClass().getAnnotation(Handler.class);
            if (handler == null) {
                throw new Error("@Handler annotation used error.");
            }
            short instruction = handler.instruction();
            handlerMap.put(instruction, messageHandler);
        });
    }

    /**
     * 获取处理类
     *
     * @param instruction 指令
     * @return 处理类
     */
    public static MessageHandler getMessageHandler(short instruction) {
        return handlerMap.get(instruction);
    }
}
