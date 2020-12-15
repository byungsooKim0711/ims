package org.kimbs.ims.channel.kakao.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.model.kakao.AtMessageRes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class AtMessageService extends AbstractMessageService<AtMessageReq, AtMessageRes> {

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder()
                .baseUrl(config.getAtBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    protected Mono<AtMessageRes> request(AtMessageReq request) {
        // 요청시간 추가
        return webClient.get()
                .exchangeToMono(exchange -> exchange.bodyToMono(AtMessageRes.class));
    }

    @Override
    protected Mono<AtMessageRes> report(AtMessageReq request, AtMessageRes response) {
        // 결과시간 추가
        log.info("[AT] report. serialNumber: {}", request.getSerialNumber());
        return Mono.just(response);
    }

    @Override
    protected Mono<AtMessageRes> history(AtMessageReq request, AtMessageRes response) {
        log.info("[AT] history. serialNumber: {}", request.getSerialNumber());
        return Mono.just(response);
    }

    @Override
    protected void log(AtMessageReq request, AtMessageRes response) {
        log.info("[AT] log. serialNumber: {}, code: {}, message: {}, responseAt: {}", request.getSerialNumber(), response.getCode(), response.getMessage(), request.getTrace().getKakaResAt());
    }
}
