package org.kimbs.ims.channel.kakao.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.channel.kakao.service.AbstractMessageService;
import org.kimbs.ims.protocol.AbstractMessage;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.support.Acknowledgment;

public abstract class AbstractMessageConsumer<T extends AbstractMessage> {

    protected AbstractMessageService<T, ?> abstractMessageService;

    public AbstractMessageConsumer(AbstractMessageService<T, ?> abstractMessageService) {
        this.abstractMessageService = abstractMessageService;
    }

    public abstract void consume(ConsumerRecord<String, ImsPacket<T>> packet, Acknowledgment ack);
}
