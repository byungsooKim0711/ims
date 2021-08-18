package org.kimbs.ims.router.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.AtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AtMessageConsumer extends AbstractMessageConsumer<AtMessageReq> {

    private final AtMessageRouter router;

    // TODO: topic, 정보 추가
    @KafkaListener(topics = "#{routerConfig.topics.recvAt}")
    @Override
    public void consume(ImsPacket<AtMessageReq> packet, Acknowledgment ack) {
        log.info("[AT-CONSUME] command: {}, trackingId: {}", packet.getCommand(), packet.getTraceInfo().getTrackingId());

        router.routeAndSend(packet);

        ack.acknowledge();
    }
}
