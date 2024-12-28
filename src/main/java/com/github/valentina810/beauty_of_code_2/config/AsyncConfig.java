package com.github.valentina810.beauty_of_code_2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig {

    @Bean(name = "transactionTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int cores = Runtime.getRuntime().availableProcessors();
        log.info("Available processors (cores): {}", cores);
        int maxPoolSize = cores * 2;
        int queueCapacity = 500;
        long maxMemory = Runtime.getRuntime().maxMemory();
        log.info("Max memory (bytes): {}", maxMemory);

        executor.setCorePoolSize(cores);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("DynamicTransactionThread-");

        executor.initialize();
        return executor;
    }
}

