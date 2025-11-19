package com.itki.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
public class AsyncConfig {
  private static final int THREAD_POOL_SIZE = 10;

  @Bean(destroyMethod = "shutdown")
  public ExecutorService getExecutor() {
    ThreadFactory virtualThreadFactory = Thread.ofVirtual().factory();
    return Executors.newFixedThreadPool(THREAD_POOL_SIZE, virtualThreadFactory);
  }
}
