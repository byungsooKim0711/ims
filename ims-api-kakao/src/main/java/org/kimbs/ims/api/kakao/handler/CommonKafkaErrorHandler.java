package org.kimbs.ims.api.kakao.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommonKafkaErrorHandler implements ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonKafkaErrorHandler.class);

    @Override
    public void handle(Exception thrownException, ConsumerRecord<?, ?> record) {
        String value = (String) record.value();
        int partition = record.partition();

        logger.error("error received message='{}' with partition-offset='{}', key='{}'", value, partition, record.key());
    }

}