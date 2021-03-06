package org.kimbs.ims.channel.kakao.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.model.kakao.AtMessageRes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Slf4j
@Service
public class AtMessageService extends AbstractMessageService<AtMessageReq, AtMessageRes> {

    private WebClient webClient;

    @PostConstruct
    public void init() {
        HttpClient httpClient = HttpClient.create().wiretap(true);

        webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(config.getAtBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    protected Mono<AtMessageRes> request(AtMessageReq request) {
        // 요청시간 추가
        request.getTrace().setKakaReqAt(LocalDateTime.now());
        return webClient.get()
                .exchangeToMono(exchange -> exchange.bodyToMono(AtMessageRes.class));
    }

    @Override
    protected Mono<AtMessageRes> report(AtMessageReq request, AtMessageRes response) {
        // 결과시간 추가
        request.getTrace().setKakaResAt(LocalDateTime.now());
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
