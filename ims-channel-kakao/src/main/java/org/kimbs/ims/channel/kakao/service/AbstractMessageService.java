package org.kimbs.ims.channel.kakao.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.channel.kakao.config.ChannelKakaoConfig;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@Slf4j
public abstract class AbstractMessageService<REQ, RES> {

    @Autowired
    protected ChannelKakaoConfig config;

    @Autowired
    protected ObjectMapper mapper;

    public void sendMessage(ImsPacket<REQ> packet) {
        convertData(packet);

        request(packet)
                .flatMap(res -> report(packet, res))
                .flatMap(res -> history(packet, res))
                .doOnError(error -> log.error("### error: {}", error.getMessage()))
                .doOnSuccess(success -> log.info("### success: {}", success))
                .subscribe(res -> log(packet, res));
    }

    protected abstract void convertData(ImsPacket<REQ> packet);
    protected abstract Mono<RES> request(ImsPacket<REQ> packet);
    protected abstract Mono<RES> report(ImsPacket<REQ> packet, RES response);
    protected abstract Mono<RES> history(ImsPacket<REQ> packet, RES response);
    protected abstract void log(ImsPacket<REQ> packet, RES response);
}
