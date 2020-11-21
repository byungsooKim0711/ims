package org.kimbs.imc.gateway.config;

import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Configuration
public class CircuitBreakerConfig {

    @Bean
    public Resilience4JCircuitBreakerFactory resilience4JCircuitBreakerFactory() {
        Resilience4JCircuitBreakerFactory factory = new Resilience4JCircuitBreakerFactory();
        factory
                .configureDefault(config -> new Resilience4JConfigBuilder(config)
                        .timeLimiterConfig(TimeLimiterConfig
                                .custom()
                                .timeoutDuration(Duration.ofMillis(3000))
                                .build())
                        .circuitBreakerConfig(io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.ofDefaults())
                        .circuitBreakerConfig(io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.custom()
                                .slidingWindowSize(10)
                                .waitDurationInOpenState(Duration.ofMillis(300))
                                .permittedNumberOfCallsInHalfOpenState(5)
                                .recordExceptions(TimeoutException.class)
//								.recordException(throwable -> throwable instanceof CircuitBreakerBaseException)
//								.ignoreException(throwable -> throwable instanceof BusinessException)
                                .build())
                        .build());

        return factory;
    }
}
