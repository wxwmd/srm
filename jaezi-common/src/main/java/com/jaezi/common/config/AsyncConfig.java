package com.jaezi.common.config;

import com.jaezi.common.manager.ThreadManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 异步处理配置类
 */

/**
 * @Async 通过注解开启异步线程处理任务
 * 指定线程池处理 @Async("scheduledExecutorService")
 */
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        return ThreadManager.getThreadPool();
    }
}
