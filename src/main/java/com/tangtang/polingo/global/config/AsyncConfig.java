package com.tangtang.polingo.global.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // Core Pool Size는 CPU 코어 수와 동일하거나 약간 더 큰 값으로 설정
        executor.setCorePoolSize(4);
        // Max Pool Size는 Core Pool Size의 2배로 설정
        executor.setMaxPoolSize(8);
        // Queue Capacity는 서버의 메모리 용량을 고려하여 설정
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("AsyncExecutor-");
        executor.initialize();
        return executor;
    }
}
