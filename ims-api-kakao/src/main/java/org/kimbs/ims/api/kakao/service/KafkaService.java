package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper mapper;
    private final KafkaTemplate<String, ImsPacket<?>> kafkaTemplate;

    public void sendToKafka(String topic, ImsPacket<?> message) throws JsonProcessingException {
        ListenableFuture<SendResult<String, ImsPacket<?>>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, ImsPacket<?>>>() {

            @Override
            public void onSuccess(SendResult<String, ImsPacket<?>> packet) {

            }

            @Override
            public void onFailure(Throwable e) {
                log.error("kafka send failed. topic: {}, data: {}", topic, message.getData(), e.getCause());
            }
        });
    }
}
