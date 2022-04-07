package com.jaezi.common.manager;

import com.jaezi.common.thread.Threads;
import com.jaezi.common.util.SpringUtils;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 任务管理器
 *
 * @author yzl
 */
public class ThreadManager {

    /**
     * 立即任务调度线程池
     */
    private static ThreadPoolExecutor threadPool = SpringUtils.getBean("threadPoolExecutorExecutor");

    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 延迟操作任务调度线程池
     */
    private static ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

    private ThreadManager() {
    }

    private static ThreadManager threadManager = new ThreadManager();

    public static ThreadManager getInstance() {
        return threadManager;
    }

    public static ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    public static ScheduledExecutorService getScheduled() {
        return executor;
    }

    /**
     * 立即执行任务
     *
     * @param runnable 任务
     */
    public void syncExecute(Runnable runnable) {
        threadPool.execute(runnable);
    }

    /**
     * 延迟执行任务
     *
     * @param task 任务
     */
    public void asyncExecute(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        Threads.shutdownAndAwaitTermination(threadPool);
        Threads.shutdownAndAwaitTermination(executor);
    }

}
