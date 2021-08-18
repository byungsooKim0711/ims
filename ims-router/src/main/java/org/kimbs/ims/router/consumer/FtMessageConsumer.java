package org.kimbs.ims.router.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.ImsPacketCommand;
import org.kimbs.ims.protocol.v1.trace.TraceInfo;
import org.kimbs.ims.router.service.FtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FtMessageConsumer extends AbstractMessageConsumer<FtMessageReq> {

    private final FtMessageRouter router;

    @KafkaListener(topics = "#{routerConfig.topics.recvFt}")
    @Override
    public void consume(ImsPacket<FtMessageReq> packet, Acknowledgment ack) {
        log.info("[FT-CONSUME] command: {}, trackingId: {}", packet.getCommand(), packet.getTraceInfo().getTrackingId());

        router.routeAndSend(packet);

        ack.acknowledge();
    }

}
