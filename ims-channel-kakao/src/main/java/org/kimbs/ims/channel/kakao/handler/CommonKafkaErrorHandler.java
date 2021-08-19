package org.kimbs.ims.channel.kakao.handler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.stereotype.Component;

@Component
public class CommonKafkaErrorHandler implements ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonKafkaErrorHandler.class);

    @Override
    public void handle(Exception exception, ConsumerRecord<?, ?> record) {
        logger.error("error received record: {}", record, exception);
    }

}
