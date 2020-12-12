package org.kimbs.ims.api.kakao.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.BatchErrorHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommonKafkaBatchErrorHandler implements BatchErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonKafkaErrorHandler.class);

    @Override
    public void handle(Exception thrownException, ConsumerRecords<?, ?> records) {
        String value = null;
        for (TopicPartition partition : records.partitions()) {
            for (ConsumerRecord<?, ?> record : records.records(partition)) {
                value = (String) record.value();
                logger.error("error received message='{}' with partition-offset='{}', key='{}'", value, partition, record.key());
            }
        }
    }

}