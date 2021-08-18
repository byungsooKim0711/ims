package org.kimbs.ims.router.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.email.EmailMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.EmMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EmMessageConsumer extends AbstractMessageConsumer<EmailMessageReq> {

    private final EmMessageRouter router;

    @KafkaListener(topics = "#{routerConfig.topics.recvEm}")
    @Override
    public void consume(ImsPacket<EmailMessageReq> packet, Acknowledgment ack) {
        log.info("[EM-CONSUME] command: {}, trackingId: {}", packet.getCommand(), packet.getTraceInfo().getTrackingId());

        router.routeAndSend(packet);

        ack.acknowledge();
    }
}
