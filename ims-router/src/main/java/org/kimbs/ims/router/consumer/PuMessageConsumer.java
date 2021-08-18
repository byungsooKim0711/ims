package org.kimbs.ims.router.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.push.PushMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.PuMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PuMessageConsumer extends AbstractMessageConsumer<PushMessageReq> {

    private final PuMessageRouter router;

    @KafkaListener(topics = "#{routerConfig.topics.recvPu}")
    @Override
    public void consume(ImsPacket<PushMessageReq> messagePacket, Acknowledgment ack) {

    }

    @Override
    public PushMessageReq convert(Object data) {
        return null;
    }
}
