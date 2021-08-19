package org.kimbs.ims.channel.kakao.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.channel.kakao.service.BtMessageService;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BtMessageConsumer extends AbstractMessageConsumer<BtMessageReq> {

    private final BtMessageService btMessageService;

    @KafkaListener(topics = "#{channelKakaoConfig.topics.sendBt}")
    @Override
    public void consume(ImsPacket<BtMessageReq> packet, Acknowledgment ack) {

        ack.acknowledge();
    }
}
