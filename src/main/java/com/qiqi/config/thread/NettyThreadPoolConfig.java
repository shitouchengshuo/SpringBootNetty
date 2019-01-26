package com.qiqi.config.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Netty配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "thread.bootstrap")
public class NettyThreadPoolConfig {

    private int worker;

    private int boss;
}
