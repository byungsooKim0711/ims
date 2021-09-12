package org.kimbs.ims.mt.config;

import lombok.RequiredArgsConstructor;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

@RequiredArgsConstructor
@Configuration
@EnableKafka
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public ReactiveKafkaProducerTemplate<String, ImsPacket<?>> reactiveKafkaProducerTemplate() {
        return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(kafkaProperties.buildProducerProperties()));
    }

}
