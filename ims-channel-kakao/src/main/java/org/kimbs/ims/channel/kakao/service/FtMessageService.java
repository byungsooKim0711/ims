package org.kimbs.ims.channel.kakao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.model.kakao.FtMessageRes;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.v1.trace.TraceFt;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class FtMessageService extends AbstractMessageService<FtMessageReq, FtMessageRes> {

    private final WebClient webClient;

    @Override
    protected Mono<FtMessageRes> request(ImsPacket<FtMessageReq> packet) {
        // 요청시간 추가
        packet.getTraceInfo().setTraceFt(TraceFt.builder().ftRequestAt(LocalDateTime.now()).build());
        return webClient.post()
                .uri(config.getFtPath())
                .bodyValue(packet.getData())
                .retrieve()
                .bodyToMono(FtMessageRes.class);
    }

    @Override
    protected Mono<FtMessageRes> report(ImsPacket<FtMessageReq> packet, FtMessageRes response) {
        packet.getTraceInfo().getTraceFt().setFtResponseAt(LocalDateTime.now());
        packet.getTraceInfo().getTraceFt().setFtMessageRes(response);

        log.info("[FT] report. serialNumber: {}", packet.getData().getSerialNumber());
        return Mono.just(response);
    }

    @Override
    protected Mono<FtMessageRes> history(ImsPacket<FtMessageReq> packet, FtMessageRes response) {
        log.info("[FT] history. serialNumber: {}", packet.getData().getSerialNumber());
        return Mono.just(response);
    }

    @Override
    protected void log(ImsPacket<FtMessageReq> packet, FtMessageRes response) {
        TraceFt ftTrace = packet.getTraceInfo().getTraceFt();
        log.info("[FT] log. serialNumber: {}, code: {}, message: {}, elapsed time: {}",
                packet.getData().getSerialNumber(), ftTrace.getFtMessageRes().getCode(), ftTrace.getFtMessageRes().getMessage(), Duration.between(ftTrace.getFtRequestAt(), ftTrace.getFtResponseAt()));
    }
}
