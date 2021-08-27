package org.kimbs.ims.router.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.BtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BtMessageConsumer extends AbstractMessageConsumer<BtMessageReq> {

    public BtMessageConsumer(BtMessageRouter messageRouter) {
        super(messageRouter);
    }

    @KafkaListener(topics = "#{routerConfig.topics.recvBt}")
    @Override
    public void consume(ConsumerRecord<String, ImsPacket<BtMessageReq>> packet, Acknowledgment ack) {
        ImsPacket<BtMessageReq> message = packet.value();

        log.info("[BT-CONSUME] command: {}, topic: {}, partition: {}, trackingId: {}", message.getCommand(), packet.topic(), packet.partition(), message.getTraceInfo().getTrackingId());

        messageRouter.routeAndSend(message);

        ack.acknowledge();
    }
}