package com.qiqi.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步日志输出，为了尽量减少日志打印对主线程造成的影响，会在socket关闭后输出
 */
@Component
public class Printer {

    private static final Logger logger = LoggerFactory.getLogger(Printer.class);

    /**
     * 异步日志打印
     *
     * @param message 日志正文
     * @param params  参数
     */
    @Async("logThreadPoolExecutor")
    public void info(String message, Object... params) {
        logger.info(message, params);
    }
}
