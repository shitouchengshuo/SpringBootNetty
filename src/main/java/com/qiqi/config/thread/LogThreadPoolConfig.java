package com.qiqi.config.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 读取日志打印需要的参数，这个线程池只是为了尽量减少日志打印对主线程造成的影响
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "thread.pool.log")
public class LogThreadPoolConfig {

    private Integer queueSize;

    private Integer corePoolSize;

    private Integer maximumPoolSize;

    private Long keepAliveTime;
}
