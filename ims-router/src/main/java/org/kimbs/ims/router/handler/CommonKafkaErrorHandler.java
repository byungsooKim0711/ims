package org.kimbs.ims.router.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonKafkaErrorHandler implements ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonKafkaErrorHandler.class);

    @Override
    public void handle(Exception exception, ConsumerRecord<?, ?> record) {
        logger.error("error received record: {}", record, exception);
    }

}
