package org.kimbs.ims.channel.kakao.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.model.kakao.BtMessageRes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class BtMessageService extends AbstractMessageService<BtMessageReq, BtMessageRes> {

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder()
                .baseUrl(config.getBtBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    protected Mono<BtMessageRes> request(BtMessageReq message) {
        return Mono.empty();
    }

    @Override
    protected Mono<BtMessageRes> report(BtMessageReq request, BtMessageRes response) {
        return Mono.just(response);
    }

    @Override
    protected Mono<BtMessageRes> history(BtMessageReq request, BtMessageRes response) {
        return Mono.just(response);
    }

    @Override
    protected void log(BtMessageReq request, BtMessageRes response) {

    }
}
