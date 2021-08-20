package org.kimbs.ims.channel.kakao.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.model.kakao.BtMessageRes;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class BtMessageService extends AbstractMessageService<BtMessageReq, BtMessageRes> {

    private final WebClient webClient;

    @Override
    protected void convertData(ImsPacket<BtMessageReq> packet) {
        packet.updateData(mapper.convertValue(packet.getData(), new TypeReference<BtMessageReq>() {}));
    }

    @Override
    protected Mono<BtMessageRes> request(ImsPacket<BtMessageReq> packet) {
        return Mono.empty();
    }

    @Override
    protected Mono<BtMessageRes> report(ImsPacket<BtMessageReq> packet, BtMessageRes response) {
        return Mono.just(response);
    }

    @Override
    protected Mono<BtMessageRes> history(ImsPacket<BtMessageReq> packet, BtMessageRes response) {
        return Mono.just(response);
    }

    @Override
    protected void log(ImsPacket<BtMessageReq> packet, BtMessageRes response) {

    }
}
