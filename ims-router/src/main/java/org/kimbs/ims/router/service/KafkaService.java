package org.kimbs.ims.router.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaService {

    private final KafkaTemplate<String, ImsPacket<?>> kafkaTemplate;

    public void sendToKafka(String topic, ImsPacket<?> message) {
        ListenableFuture<SendResult<String, ImsPacket<?>>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, ImsPacket<?>>>() {
            @Override
            public void onSuccess(SendResult<String, ImsPacket<?>> result) {
            }

            @Override
            public void onFailure(Throwable e) {
                log.error("kafka send failed. topic: {}, data: {}", topic, message, e.getCause());
            }
        });
    }
}