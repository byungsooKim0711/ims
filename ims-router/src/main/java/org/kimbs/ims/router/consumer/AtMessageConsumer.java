package org.kimbs.ims.router.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.AtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AtMessageConsumer extends AbstractMessageConsumer<AtMessageReq> {

    public AtMessageConsumer(AtMessageRouter messageRouter) {
        super(messageRouter);
    }

    @KafkaListener(topics = "#{routerConfig.topics.recvAt}")
    @Override
    public void consume(ConsumerRecord<String, ImsPacket<AtMessageReq>> packet, Acknowledgment ack) {
        ImsPacket<AtMessageReq> message = packet.value();

        log.info("[AT-CONSUME] command: {}, topic: {}, partition: {}, trackingId: {}", message.getCommand(), packet.topic(), packet.partition(), message.getTraceInfo().getTrackingId());

        messageRouter.routeAndSend(packet.value());

        ack.acknowledge();
    }
}
