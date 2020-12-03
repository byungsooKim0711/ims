package org.kimbs.ims.api.kakao.controller.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Component
public class ImsRequestFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 여기서 pathVariable - serviceKey 체크?
        // requestBody에 접수 시간 추가?

        log.info("#### path: {}", exchange.getRequest().getPath());
        log.info("#### path[5,6]: {}", exchange.getRequest().getPath().subPath(5, 6));
        log.info("#### body: {}", exchange.getRequest().getBody());
        log.info("#### id: {}", exchange.getRequest().getId());
        log.info("#### method: {}", exchange.getRequest().getMethod());
        log.info("#### header: {}", exchange.getRequest().getHeaders().getFirst("IMS-SERVICE-KEY"));
        log.info("#### header: {}", exchange.getRequest().getHeaders().getFirst("IMS-VERSION"));

        URI originUri = exchange.getRequest().getURI();

        try {
            URI mutatedUri = new URI("http",
                    originUri.getUserInfo(),
                    originUri.getHost(),
                    originUri.getPort(),
                    "/ims/v1/test/test",
                    originUri.getQuery(),
                    originUri.getFragment());


            return chain.filter(exchange.mutate().request(req -> req.uri(mutatedUri)).build());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
}
