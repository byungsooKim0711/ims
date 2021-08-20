package org.kimbs.ims.channel.kakao.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.model.kakao.AtMessageRes;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.v1.trace.TraceAt;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class AtMessageService extends AbstractMessageService<AtMessageReq, AtMessageRes> {

    private final WebClient webClient;

    @Override
    protected void convertData(ImsPacket<AtMessageReq> packet) {
        packet.updateData(mapper.convertValue(packet.getData(), new TypeReference<AtMessageReq>() {}));
    }

    @Override
    protected Mono<AtMessageRes> request(ImsPacket<AtMessageReq> packet) {
        // 요청시간 추가
        packet.getTraceInfo().setTraceAt(TraceAt.builder().atRequestAt(LocalDateTime.now()).build());
        return webClient.post()
                .uri(config.getAtPath())
                .bodyValue(packet.getData())
                .retrieve()
                .bodyToMono(AtMessageRes.class);
    }

    @Override
    protected Mono<AtMessageRes> report(ImsPacket<AtMessageReq> packet, AtMessageRes response) {
        // 카카오 응답.
        packet.getTraceInfo().getTraceAt().setAtResponseAt(LocalDateTime.now());
        packet.getTraceInfo().getTraceAt().setAtMessageRes(response);

        log.info("[AT] report. serialNumber: {}", packet.getData().getSerialNumber());
        return Mono.just(response);
    }

    @Override
    protected Mono<AtMessageRes> history(ImsPacket<AtMessageReq> packet, AtMessageRes response) {
        log.info("[AT] history. serialNumber: {}", packet.getData().getSerialNumber());
        return Mono.just(response);
    }

    @Override
    protected void log(ImsPacket<AtMessageReq> packet, AtMessageRes response) {
        TraceAt atTrace = packet.getTraceInfo().getTraceAt();
        log.info("[AT] log. serialNumber: {}, code: {}, message: {}, elapsed time: {}",
                packet.getData().getSerialNumber(), atTrace.getAtMessageRes().getCode(), atTrace.getAtMessageRes().getMessage(), Duration.between(atTrace.getAtRequestAt(), atTrace.getAtResponseAt()));
    }
}
