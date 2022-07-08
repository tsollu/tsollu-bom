package com.kaddo.framework.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

/**
 * Customize the KaddoExecutorService.
 */
@Slf4j
@Configuration
public class KaddoExecutorService {

    private static final String EXCEPTION_MESSAGE = "Caught async exception";
    private final ThreadPoolTaskExecutor taskExecutor;
    private final ThreadPoolTaskScheduler taskScheduler;

    public KaddoExecutorService(ThreadPoolTaskExecutor taskExecutor, ThreadPoolTaskScheduler taskScheduler) {
        this.taskExecutor = taskExecutor;
        this.taskScheduler = taskScheduler;
    }

    private <T> Callable<T> wrappedCallable(final Callable<T> task) {
        return () -> {
            try {
                return task.call();
            } catch (Exception e) {
                handle(e);
                throw e;
            }
        };
    }

    private Runnable wrappedRunnable(final Runnable task) {
        return () -> {
            try {
                task.run();
            } catch (Exception e) {
                handle(e);
            }
        };
    }

    private void handle(Throwable e) {
        log.error(EXCEPTION_MESSAGE, e);
    }

    public ThreadPoolTaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public ThreadPoolTaskScheduler getTaskScheduler() {
        return taskScheduler;
    }

    public void execute(Runnable task) {
        taskExecutor.execute(wrappedRunnable(task));
    }

    public void submit(Runnable task) {
        taskExecutor.submit(wrappedRunnable(task));
    }

    public <T> Future<T> submit(Callable<T> task) {
        return taskExecutor.submit(wrappedCallable(task));
    }

    public ListenableFuture<?> submitListenable(Runnable task) {
        return taskExecutor.submitListenable(wrappedRunnable(task));
    }

    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        return taskExecutor.submitListenable(wrappedCallable(task));
    }

}
