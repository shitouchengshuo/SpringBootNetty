package com.qiqi.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.qiqi.config.LogThreadPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.Assert;
import java.util.concurrent.*;

/**
 * 线程池配置（yml没有相关配置，故自己写）
 */
@EnableAsync
@Configuration
public class CustomerExecutors {

    /**
     * 日志打印线程
     */
    private static final ThreadFactory LOG_THREAD_FACTORY =
            new ThreadFactoryBuilder().setNameFormat("LOG-THREAD-%d").build();


    private LogThreadPoolConfig logThreadPoolConfig;



    // @Autowired
    // public CustomerExecutors(LogThreadPoolConfig logThreadPoolConfig) {
    //     this.logThreadPoolConfig = logThreadPoolConfig;
    //     Assert.notNull(this.logThreadPoolConfig, "log thread pool init fail.");
    // }


    /**
     * 日志打印线程，如果线程池处理不过来就交给调用者去处理（日志打印一般不会消耗太多系统资源，故可以将处理不过来的日志交给调用
     * 者去处理）（饱和策略：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。
     *  CallerRunsPolicy策略：意思是只用调用者所在线程来运行任务。）
     */
    // @Bean(name = "logThreadPoolExecutor")
    // public Executor logThreadPoolExecutor() {
    //     return new ThreadPoolExecutor(logThreadPoolConfig.getCorePoolSize(), logThreadPoolConfig.getMaximumPoolSize(),
    //             logThreadPoolConfig.getKeepAliveTime(), TimeUnit.SECONDS,
    //             new LinkedBlockingQueue<>(logThreadPoolConfig.getQueueSize()), LOG_THREAD_FACTORY,
    //             new ThreadPoolExecutor.CallerRunsPolicy());
    // }


}
