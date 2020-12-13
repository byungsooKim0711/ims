package org.kimbs.ims.router.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BtMessageConsumer {

    private final ObjectMapper mapper;

    @KafkaListener(topics = "#{routerConfig.topics.recvBt}")
    public void consume(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        int partition = record.partition();
        String topic = record.topic();

//        if (log.isDebugEnabled()) {
            log.info("received message={} with topic={}-{}", value, topic, partition);
//        }
    }
}
