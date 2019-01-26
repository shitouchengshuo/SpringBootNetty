package com.qiqi.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步日志输出
 */
@Component
public class Printer {

    private static final Logger logger = LogManager.getLogger(Printer.class);

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
