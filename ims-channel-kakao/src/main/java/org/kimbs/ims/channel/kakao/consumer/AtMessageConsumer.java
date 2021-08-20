package org.kimbs.ims.channel.kakao.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.channel.kakao.service.AtMessageService;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AtMessageConsumer extends AbstractMessageConsumer<AtMessageReq> {

    private final AtMessageService atMessageService;

    @KafkaListener(topics = "#{channelKakaoConfig.topics.sendAt}")
    @Override
    public void consume(ImsPacket<AtMessageReq> packet, Acknowledgment ack) {
        atMessageService.sendMessage(packet);

        ack.acknowledge();
    }
}
