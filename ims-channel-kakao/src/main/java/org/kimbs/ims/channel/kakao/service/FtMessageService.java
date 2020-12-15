package org.kimbs.ims.channel.kakao.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.model.kakao.FtMessageRes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class FtMessageService extends AbstractMessageService<FtMessageReq, FtMessageRes> {

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder()
                .baseUrl(config.getFtBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    protected Mono<FtMessageRes> request(FtMessageReq message) {
        return Mono.empty();
    }

    @Override
    protected Mono<FtMessageRes> report(FtMessageReq request, FtMessageRes response) {
        return Mono.just(response);
    }

    @Override
    protected Mono<FtMessageRes> history(FtMessageReq request, FtMessageRes response) {
        return Mono.just(response);
    }

    @Override
    protected void log(FtMessageReq request, FtMessageRes response) {

    }
}
