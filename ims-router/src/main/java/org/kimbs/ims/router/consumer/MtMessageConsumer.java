package org.kimbs.ims.router.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.mt.MtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.MtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MtMessageConsumer extends AbstractMessageConsumer<MtMessageReq> {

    public MtMessageConsumer(MtMessageRouter messageRouter) {
        super(messageRouter);
    }

    @KafkaListener(topics = "#{routerConfig.topics.recvMt}")
    @Override
    public void consume(ConsumerRecord<String, ImsPacket<MtMessageReq>> packet, Acknowledgment ack) {
        ImsPacket<MtMessageReq> message = packet.value();

        log.info("[MT-CONSUME] command: {}, topic: {}, partition: {}, trackingId: {}", message.getCommand(), packet.topic(), packet.partition(), message.getTraceInfo().getTrackingId());

        messageRouter.routeAndSend(message);

        ack.acknowledge();
    }
}
