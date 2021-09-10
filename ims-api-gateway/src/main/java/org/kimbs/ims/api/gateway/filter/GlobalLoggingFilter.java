package org.kimbs.ims.api.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Configuration
public class GlobalLoggingFilter {

    @Bean
    public GlobalFilter postGlobalFilter() {
        LocalDateTime startDate = LocalDateTime.now();

        return ((exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    String requestPath = exchange.getRequest().getPath().toString();
                    String requestId = exchange.getRequest().getId();

                    log.info("Global logging filter. path: {}, id: {}, elapsedTime: {}", requestPath, requestId, Duration.between(startDate, LocalDateTime.now()));
                }))
        );
    }
}
