package org.kimbs.ims.router.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.FtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FtMessageConsumer extends AbstractMessageConsumer<FtMessageReq> {

    public FtMessageConsumer(FtMessageRouter messageRouter) {
        super(messageRouter);
    }

    @KafkaListener(topics = "#{routerConfig.topics.recvFt}")
    @Override
    public void consume(ConsumerRecord<String, ImsPacket<FtMessageReq>> packet, Acknowledgment ack) {
        ImsPacket<FtMessageReq> message = packet.value();

        log.info("[FT-CONSUME] command: {}, topic: {}, partition: {}, trackingId: {}", message.getCommand(), packet.topic(), packet.partition(), message.getTraceInfo().getTrackingId());

        messageRouter.routeAndSend(message);

        ack.acknowledge();
    }

}
