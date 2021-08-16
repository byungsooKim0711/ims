package org.kimbs.ims.api.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.kimbs.ims.util.AuthUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    // add configuration.
    @Data
    public static class Config {

    }

    public AuthFilter(ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
        super(Config.class);
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // no authorization header
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            // empty authorization header
            String authorizationHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isEmpty(authorizationHeader)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            String encodedAuthKey = AuthUtil.getAuthKey(authorizationHeader);
            String authKey = AuthUtil.decodeApiKey(encodedAuthKey);

            // check valid
            if (!isValid(authKey)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            log.info("[AUTH] request. authKey: {}", authKey);

            // add header ?
            ServerHttpRequest mutateRequest = request.mutate()
                    .build();

            return chain.filter(exchange.mutate().request(mutateRequest).build())
                    .doOnError(e -> log.error("[AUTH] response. authKey: {}", authKey, e))
                    .doOnSuccess(s -> log.info("[AUTH] response. authKey: {}", authKey));
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        return response.setComplete();
    }

    private boolean isValid(String decodedAuthKey) {
        String username = AuthUtil.getUsername(decodedAuthKey, ":");
        String secretKey = AuthUtil.getSecretKey(decodedAuthKey, ":");

        // redis check.
//        reactiveRedisTemplate.opsForHash().get()
        return true;
    }
}
