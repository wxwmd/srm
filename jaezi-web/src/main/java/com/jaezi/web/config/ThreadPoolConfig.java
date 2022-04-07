package com.jaezi.web.config;

import com.jaezi.common.thread.Threads;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池配置
 *
 * @author yzl
 **/
@Configuration
public class ThreadPoolConfig {
    // 核心线程池大小
    private int corePoolSize = 5;

    // 最大可创建的线程数
    private int maxPoolSize = 50;

    // 队列最大长度
    private int queueCapacity = 100;

    // 线程池维护线程所允许的空闲时间
    private int keepAliveSeconds = 120;

    /**
     * 初始化线程池
     */
    @Bean(name = "threadPoolExecutorExecutor")
    public ThreadPoolExecutor threadPoolExecutorExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS,
                new LinkedBlockingQueue(queueCapacity), new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    /**
     * 执行周期性或定时任务
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.printException(r, t);
            }
        };
    }
}
