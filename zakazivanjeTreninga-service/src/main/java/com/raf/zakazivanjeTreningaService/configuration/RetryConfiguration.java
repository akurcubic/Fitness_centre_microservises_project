package com.raf.zakazivanjeTreningaService.configuration;

import com.raf.zakazivanjeTreningaService.exception.NotFoundException;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class RetryConfiguration {

    @Bean
    public Retry rezervacijaServiceRetry(){

        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(5)
                .waitDuration(Duration.ofSeconds(5))
                .ignoreExceptions(NotFoundException.class)
                .build();

        RetryRegistry retryRegistry = RetryRegistry.of(retryConfig);
        return retryRegistry.retry("rezervacijaServiceRetry");
    }
}
