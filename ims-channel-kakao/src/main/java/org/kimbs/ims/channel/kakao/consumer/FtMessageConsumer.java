package org.kimbs.ims.channel.kakao.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.channel.kakao.service.FtMessageService;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FtMessageConsumer extends AbstractMessageConsumer<FtMessageReq> {

    private final FtMessageService ftMessageService;

    @KafkaListener(topics = "#{channelKakaoConfig.topics.sendFt}")
    @Override
    public void consume(ImsPacket<FtMessageReq> packet, Acknowledgment ack) {
        ftMessageService.sendMessage(packet);

        ack.acknowledge();
    }
}
