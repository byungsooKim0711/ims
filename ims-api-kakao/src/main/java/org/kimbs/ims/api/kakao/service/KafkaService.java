package org.kimbs.ims.api.kakao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaService {

    private final ReactiveKafkaProducerTemplate<String, ImsPacket<?>> reactiveKafkaProducerTemplate;

    public Mono<SenderResult<Void>> sendToKafka(String topic, ImsPacket<?> message) {
        ProducerRecord<String, ImsPacket<?>> producerRecord = new ProducerRecord<>(topic, message);

        return reactiveKafkaProducerTemplate.send(producerRecord)
                .doOnSuccess(this::onSuccess)
                .doOnError(e -> onError(e, topic, message));
    }

    private void onSuccess(SenderResult<Void> senderResult) {
        RecordMetadata metadata = senderResult.recordMetadata();
        log.info("kafka send success. topic: {}, partition: {}, offset: {}", metadata.topic(), metadata.partition(), metadata.offset());
    }

    private void onError(Throwable e, String topic, ImsPacket<?> message) {
        log.error("kafka send failed. topic: {}, data: {}", topic, message.getData(), e);
    }
}
