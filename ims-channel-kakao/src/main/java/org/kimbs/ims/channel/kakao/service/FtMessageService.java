package org.kimbs.ims.channel.kakao.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.model.kakao.FtMessageRes;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class FtMessageService extends AbstractMessageService<FtMessageReq, FtMessageRes> {

    private final WebClient webClient;

    @Override
    protected void convertData(ImsPacket<FtMessageReq> packet) {
        packet.updateData(mapper.convertValue(packet.getData(), new TypeReference<FtMessageReq>() {}));
    }

    @Override
    protected Mono<FtMessageRes> request(ImsPacket<FtMessageReq> packet) {
        return Mono.empty();
    }

    @Override
    protected Mono<FtMessageRes> report(ImsPacket<FtMessageReq> packet, FtMessageRes response) {
        return Mono.just(response);
    }

    @Override
    protected Mono<FtMessageRes> history(ImsPacket<FtMessageReq> packet, FtMessageRes response) {
        return Mono.just(response);
    }

    @Override
    protected void log(ImsPacket<FtMessageReq> packet, FtMessageRes response) {

    }
}
