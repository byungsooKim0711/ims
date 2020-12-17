package org.kimbs.ims.channel.kakao.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.channel.kakao.config.ChannelKakaoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@Slf4j
public abstract class AbstractMessageService<REQ, RES> {

    @Autowired
    protected ChannelKakaoConfig config;

    public void sendMessage(REQ request) {
        request(request)
                .flatMap(res -> report(request, res))
                .flatMap(res -> history(request, res))
                .doOnError(error -> log.error("### error: {}", error.getMessage()))
                .doOnSuccess(success -> log.info("### success: {}", success))
                .subscribe(res -> log(request, res));
    }

    protected abstract Mono<RES> request(REQ request);
    protected abstract Mono<RES> report(REQ request, RES response);
    protected abstract Mono<RES> history(REQ request, RES response);
    protected abstract void log(REQ request, RES response);
}
