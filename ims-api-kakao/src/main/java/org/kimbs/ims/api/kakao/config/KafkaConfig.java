package org.kimbs.ims.api.kakao.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.kimbs.ims.api.kakao.handler.CommonKafkaBatchErrorHandler;
import org.kimbs.ims.api.kakao.handler.CommonKafkaErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.retries}")
    private int producerRetries = 0;

    @Value("${spring.kafka.producer.batch-size}")
    private int producerBatchSize = 20000;

    @Value("${spring.kafka.producer.compression-type}")
    private String producerCompressionType = "gzip";

    @Value("${spring.kafka.producer.linger-ms}")
    private long producerLingerMs = 50;

    @Value("${spring.kafka.producer.buffer-memory}")
    private long producerBufferMemory = 335544320;

    @Value("${spring.kafka.producer.key-serializer}")
    private String producerKeySerializer = "org.apache.kafka.common.serialization.StringSerializer";

    @Value("${spring.kafka.producer.value-serializer}")
    private String producerValueSerializer = "org.apache.kafka.common.serialization.StringSerializer";

    private final CommonKafkaErrorHandler commonErrorHandler;
    private final CommonKafkaBatchErrorHandler commonBatchErrorHandler;

    @PostConstruct
    public void init() {
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG, producerRetries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, producerBatchSize);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, producerCompressionType);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producerKeySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producerValueSerializer);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, producerBufferMemory);
        props.put(ProducerConfig.LINGER_MS_CONFIG, producerLingerMs);

        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
