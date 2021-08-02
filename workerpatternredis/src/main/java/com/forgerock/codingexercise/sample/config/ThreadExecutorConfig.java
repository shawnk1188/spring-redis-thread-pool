package com.forgerock.codingexercise.sample.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadExecutorConfig {
     /**
     * Create a thread pool
     * @return
     */
    @Bean(name = "threadTaskExecutor")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);   //Core thread pool size
        executor.setMaxPoolSize(50);    //Maximum thread pool size
        executor.setQueueCapacity(1000);    //Task queue size
        executor.setKeepAliveSeconds(300);  //Timeout in seconds for idle threads in the thread pool to wait for work
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());    //Thread rejection strategy, which provides a simple feedback control mechanism, can slow down the submission of new tasks

        return executor;
    }

    /**
     * Create a fixed size thread pool
     * @return
     */
    @Bean(name = "fixedThreadPool")
    public ExecutorService executorService(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        return fixedThreadPool;
    }
}
