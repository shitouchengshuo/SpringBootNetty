package com.qiqi.config.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 这个线程池主要是为了对业务中可能造成的阻塞进行分担，减少对主线程造成的阻塞
 */
@Data
@Configuration
@ConfigurationProperties("thread.pool.chief")
public class ChiefThreadPoolConfig {

    private Integer queueSize;

    private Integer corePoolSize;

    private Integer maximumPoolSize;

    private Long keepAliveTime;
}
