package org.kimbs.ims.channel.kakao.consumer;

import org.kimbs.ims.protocol.AbstractMessage;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;

public abstract class AbstractMessageConsumer<T extends AbstractMessage> implements DisposableBean {

    protected ReactiveKafkaConsumerTemplate<String, ImsPacket<T>> consumer;

    public AbstractMessageConsumer(ReactiveKafkaConsumerTemplate<String, ImsPacket<T>> consumer) {
        this.consumer = consumer;
    }

    public abstract void consume();

    @Override
    public abstract void destroy() throws Exception;
}
