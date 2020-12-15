package org.kimbs.ims.channel.kakao.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.channel.kakao.service.AtMessageService;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AtMessageConsumer {

    private final ObjectMapper mapper;
    private final AtMessageService atMessageService;

    @KafkaListener(topics = "#{channelKakaoConfig.topics.sendAt}")
    public void consume(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        int partition = record.partition();
        String topic = record.topic();

//        if (log.isDebugEnabled()) {
            log.info("received message={} with topic={}-{}", value, topic, partition);
//        }

        try {
            AtMessageReq message = mapper.readValue(value, AtMessageReq.class);

            atMessageService.sendMessage(message);
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
