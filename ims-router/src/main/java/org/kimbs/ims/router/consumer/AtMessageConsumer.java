package org.kimbs.ims.router.consumer;

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

        log.info("{}", messagePacket);

//        if (log.isDebugEnabled()) {
//            log.debug("received message={} with topic={}-{}", value, topic, partition);
//        }
//
//        try {
//            AtMessageReq request = mapper.readValue(value, AtMessageReq.class);
//
//            router.routeAndSend(request);
//        } catch (JsonProcessingException e) {
//            log.error("json parse error. data: {}", value);
//        } catch (Exception e) {
//            log.error("Exception occurred.", e);
//
//            throw new RuntimeException(e);
//        }

        ack.acknowledge();
    }
}
