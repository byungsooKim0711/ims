package org.kimbs.ims.channel.kakao.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.channel.kakao.service.AtMessageService;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AtMessageConsumer extends AbstractMessageConsumer<AtMessageReq> {

    public AtMessageConsumer(AtMessageService atMessageService) {
        super(atMessageService);
    }

    @KafkaListener(topics = "#{channelKakaoConfig.topics.sendAt}")
    @Override
    public void consume(ConsumerRecord<String, ImsPacket<AtMessageReq>> packet, Acknowledgment ack) {
        ImsPacket<AtMessageReq> message = packet.value();
        String topic = packet.topic();
        int partition = packet.partition();
        long offset = packet.offset();

        log.info("[command: {}] topic: {}, partition: {}, offset: {}, traceId: {}", message.getCommand(), topic, partition, offset, message.getTraceInfo().getTrackingId());

        abstractMessageService.sendMessage(message);

        ack.acknowledge();
    }
}
