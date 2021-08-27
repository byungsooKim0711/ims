package org.kimbs.ims.channel.kakao.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.channel.kakao.service.BtMessageService;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BtMessageConsumer extends AbstractMessageConsumer<BtMessageReq> {


    public BtMessageConsumer(BtMessageService btMessageService) {
        super(btMessageService);
    }

    @KafkaListener(topics = "#{channelKakaoConfig.topics.sendBt}")
    @Override
    public void consume(ConsumerRecord<String, ImsPacket<BtMessageReq>> packet, Acknowledgment ack) {
        ImsPacket<BtMessageReq> message = packet.value();
        String topic = packet.topic();
        int partition = packet.partition();
        long offset = packet.offset();

        log.info("[command: {}] topic: {}, partition: {}, offset: {}, traceId: {}", message.getCommand(), topic, partition, offset, message.getTraceInfo().getTrackingId());

        abstractMessageService.sendMessage(message);

        ack.acknowledge();
    }
}
