package org.kimbs.ims.api.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class GlobalLoggingFilter {

    @Bean
    public GlobalFilter postGlobalFilter() {
        return ((exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    String requestPath = exchange.getRequest().getPath().toString();
                    String requestId = exchange.getRequest().getId();

                    log.info("Global logging filter. path: {}, id: {}", requestPath, requestId);
                }))
        );
    }
}
