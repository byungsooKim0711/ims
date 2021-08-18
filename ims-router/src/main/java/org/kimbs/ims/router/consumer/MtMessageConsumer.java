package org.kimbs.ims.router.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.mt.MtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.MtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MtMessageConsumer extends AbstractMessageConsumer<MtMessageReq> {

    private final MtMessageRouter router;

    @KafkaListener(topics = "#{routerConfig.topics.recvMt}")
    @Override
    public void consume(ImsPacket<MtMessageReq> packet, Acknowledgment ack) {
        log.info("[MT-CONSUME] command: {}, trackingId: {}", packet.getCommand(), packet.getTraceInfo().getTrackingId());

        router.routeAndSend(packet);

        ack.acknowledge();
    }
}
