package org.kimbs.ims.router.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.AtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AtMessageConsumer extends AbstractMessageConsumer<AtMessageReq> {

    private final ObjectMapper mapper;
    private final AtMessageRouter router;

    // TODO: topic, 정보 추가
    @KafkaListener(topics = "#{routerConfig.topics.recvAt}")
    @Override
    public void consume(ImsPacket<AtMessageReq> messagePacket, Acknowledgment ack) {

        messagePacket.updateData(this.convert(messagePacket.getData()));

        try {
            router.routeAndSend(messagePacket);
        } catch (Exception e) {
            log.error("", e);
        }

        ack.acknowledge();
    }

    @Override
    public AtMessageReq convert(Object data) {
        return mapper.convertValue(data, new TypeReference<AtMessageReq>() {}) ;
    }
}
