package org.kimbs.ims.router.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaService {

    private final KafkaTemplate<String, ImsPacket<?>> kafkaTemplate;

    public void sendToKafka(String topic, ImsPacket<?> message) {
        Message<ImsPacket<?>> packetMessage = MessageBuilder
                .<ImsPacket<?>>withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        ListenableFuture<SendResult<String, ImsPacket<?>>> future = kafkaTemplate.send(packetMessage);
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
