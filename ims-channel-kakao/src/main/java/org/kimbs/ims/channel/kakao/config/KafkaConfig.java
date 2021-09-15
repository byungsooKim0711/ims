package org.kimbs.ims.channel.kakao.config;

import lombok.RequiredArgsConstructor;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

@RequiredArgsConstructor
@Configuration
@EnableKafka
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;
    private final ChannelKakaoConfig config;

    @Bean
    public ReactiveKafkaProducerTemplate<String, ImsPacket<?>> reactiveKafkaProducerTemplate() {
        return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(kafkaProperties.buildProducerProperties()));
    }

    @Bean("atConsumer")
    public ReactiveKafkaConsumerTemplate<String, ImsPacket<AtMessageReq>> atConsumer() {
        ReceiverOptions<String, ImsPacket<AtMessageReq>> options = ReceiverOptions.<String, ImsPacket<AtMessageReq>>create(kafkaProperties.buildConsumerProperties())
                .subscription(config.getTopics().getSendAt());

        return new ReactiveKafkaConsumerTemplate<>(options);
    }

    @Bean("ftConsumer")
    public ReactiveKafkaConsumerTemplate<String, ImsPacket<FtMessageReq>> ftConsumer() {
        ReceiverOptions<String, ImsPacket<FtMessageReq>> options = ReceiverOptions.<String, ImsPacket<FtMessageReq>>create(kafkaProperties.buildConsumerProperties())
                .subscription(config.getTopics().getSendFt());

        return new ReactiveKafkaConsumerTemplate<>(options);
    }

    @Bean("btConsumer")
    public ReactiveKafkaConsumerTemplate<String, ImsPacket<BtMessageReq>> btConsumer() {
        ReceiverOptions<String, ImsPacket<BtMessageReq>> options = ReceiverOptions.<String, ImsPacket<BtMessageReq>>create(kafkaProperties.buildConsumerProperties())
                .subscription(config.getTopics().getSendBt());

        return new ReactiveKafkaConsumerTemplate<>(options);
    }
}
