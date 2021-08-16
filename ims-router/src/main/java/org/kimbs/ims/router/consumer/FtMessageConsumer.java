package org.kimbs.ims.router.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.FtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FtMessageConsumer extends AbstractMessageConsumer<FtMessageReq> {

    private final FtMessageRouter router;

    @KafkaListener(topics = "#{routerConfig.topics.recvFt}")
    @Override
    public void consume(ImsPacket<FtMessageReq> messagePacket, Acknowledgment ack) {

    }
}
