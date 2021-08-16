package org.kimbs.ims.api.kakao.config;

import lombok.RequiredArgsConstructor;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
@EnableKafka
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public Map<String, Object> producerConfigs() {
        return kafkaProperties.buildProducerProperties();
    }

    @Bean
    public ProducerFactory<String, ImsPacket<?>> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, ImsPacket<?>> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
