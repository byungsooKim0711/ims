package org.kimbs.ims.router.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.router.service.AtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AtMessageConsumer {

    private final ObjectMapper mapper;
    private final AtMessageRouter router;

    @KafkaListener(topics = "#{routerConfig.topics.recvAt}")
    public void consume(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        int partition = record.partition();
        String topic = record.topic();

        if (log.isDebugEnabled()) {
            log.debug("received message={} with topic={}-{}", value, topic, partition);
        }

        try {
            AtMessageReq request = mapper.readValue(value, AtMessageReq.class);

            router.routeAndSend(request);
        } catch (JsonProcessingException e) {
            log.error("json parse error. data: {}", value);
        } catch (Exception e) {
            log.error("Exception occurred.", e);

            // retry
            throw new RuntimeException(e);
        }

        ack.acknowledge();
    }
}
