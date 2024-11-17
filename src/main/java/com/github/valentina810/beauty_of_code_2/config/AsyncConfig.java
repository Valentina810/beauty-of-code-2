package com.github.valentina810.beauty_of_code_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "transactionTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // Определение количества ядер процессора
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors (cores): " + cores);

        // Настройка пула потоков на основе данных системы
        int corePoolSize = cores; // Основное количество потоков = количество ядер процессора
        int maxPoolSize = cores * 2; // Максимум потоков в 2 раза больше количества ядер
        int queueCapacity = 500; // Можно динамически настроить в зависимости от задачи

        // Дополнительно можно рассчитать параметры на основе памяти:
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("Max memory (bytes): " + maxMemory);

        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("DynamicTransactionThread-");

        executor.initialize();
        return executor;
    }
}

