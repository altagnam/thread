package br.mg.gnam.pool.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadConfig {
	
    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("###### processors: " + processors);
       
        
        executor.setCorePoolSize(processors / 3);
        executor.setMaxPoolSize(processors);
        
        executor.setQueueCapacity(20);
        executor.setThreadNamePrefix("active_mq");
        executor.initialize();
        return executor;
    }
}