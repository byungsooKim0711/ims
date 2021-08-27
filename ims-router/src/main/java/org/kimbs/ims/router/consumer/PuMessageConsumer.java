package org.kimbs.ims.router.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.push.PushMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.PuMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PuMessageConsumer extends AbstractMessageConsumer<PushMessageReq> {

    public PuMessageConsumer(PuMessageRouter messageRouter) {
        super(messageRouter);
    }

    @KafkaListener(topics = "#{routerConfig.topics.recvPu}")
    @Override
    public void consume(ConsumerRecord<String, ImsPacket<PushMessageReq>> packet, Acknowledgment ack) {
        ImsPacket<PushMessageReq> message = packet.value();

        log.info("[PU-CONSUME] command: {}, topic: {}, partition: {}, trackingId: {}", message.getCommand(), packet.topic(), packet.partition(), message.getTraceInfo().getTrackingId());

        messageRouter.routeAndSend(message);

        ack.acknowledge();
    }
}
