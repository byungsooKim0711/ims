package org.kimbs.ims.router.config;

import lombok.RequiredArgsConstructor;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.handler.CommonKafkaBatchErrorHandler;
import org.kimbs.ims.router.handler.CommonKafkaErrorHandler;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

@RequiredArgsConstructor
@Configuration
@EnableKafka
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;
    private final CommonKafkaErrorHandler commonErrorHandler;
    private final CommonKafkaBatchErrorHandler commonBatchErrorHandler;

    @Bean
    ConcurrentKafkaListenerContainerFactory<Object, ImsPacket<?>> kafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        ConcurrentKafkaListenerContainerFactory<Object, ImsPacket<?>> factory = new ConcurrentKafkaListenerContainerFactory<>();

        KafkaProperties.Listener.Type type = kafkaProperties.getListener().getType();

        switch (type) {
            case SINGLE:
                factory.setErrorHandler(commonErrorHandler);
                factory.setBatchListener(false);
                break;
            case BATCH:
                factory.setBatchErrorHandler(commonBatchErrorHandler);
                factory.setBatchListener(true);
                break;
            default:
                break;
        }

        factory.setAutoStartup(true);
        factory.setConsumerFactory(consumerFactory());

        // TODO: 깔끔하게 세팅하는방법이 없을까? ;;
        factory.getContainerProperties().setAckMode(kafkaProperties.getListener().getAckMode());
        factory.getContainerProperties().setPollTimeout(kafkaProperties.getListener().getPollTimeout().toMillis());
        factory.setConcurrency(kafkaProperties.getListener().getConcurrency());

        return factory;
    }

    @Bean
    public ProducerFactory<String, ImsPacket<?>> producerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
    }

    @Bean
    public ConsumerFactory<Object, ImsPacket<?>> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }

    @Bean
    public KafkaTemplate<String, ImsPacket<?>> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}