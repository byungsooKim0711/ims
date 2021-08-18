package org.kimbs.ims.router.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.BtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BtMessageConsumer extends AbstractMessageConsumer<BtMessageReq> {

    private final BtMessageRouter router;

    @KafkaListener(topics = "#{routerConfig.topics.recvBt}")
    @Override
    public void consume(ImsPacket<BtMessageReq> packet, Acknowledgment ack) {
        log.info("[BT-CONSUME] command: {}, trackingId: {}", packet.getCommand(), packet.getTraceInfo().getTrackingId());

        router.routeAndSend(packet);

        ack.acknowledge();
    }
}