package com.kit.framework.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig implements AsyncConfigurer {
    @Value("${map.baidu.async:25}")
    int async;

    @Bean(name = "weatherQueryExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(async);
        executor.setMaxPoolSize(async);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsyncThread-Query-Weather-");
        executor.initialize();
        return executor;
    }
}
