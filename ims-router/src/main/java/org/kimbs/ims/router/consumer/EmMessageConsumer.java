package org.kimbs.ims.router.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.email.EmailMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.EmMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmMessageConsumer extends AbstractMessageConsumer<EmailMessageReq> {

    public EmMessageConsumer(EmMessageRouter messageRouter) {
        super(messageRouter);
    }

    @KafkaListener(topics = "#{routerConfig.topics.recvEm}")
    @Override
    public void consume(ConsumerRecord<String, ImsPacket<EmailMessageReq>> packet, Acknowledgment ack) {
        ImsPacket<EmailMessageReq> message = packet.value();

        log.info("[EM-CONSUME] command: {}, topic: {}, partition: {}, trackingId: {}", message.getCommand(), packet.topic(), packet.partition(), message.getTraceInfo().getTrackingId());

        messageRouter.routeAndSend(message);

        ack.acknowledge();
    }
}
