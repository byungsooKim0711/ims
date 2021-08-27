package org.kimbs.ims.router.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.protocol.AbstractMessage;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.service.AbstractMessageRouter;
import org.springframework.kafka.support.Acknowledgment;

public abstract class AbstractMessageConsumer<T extends AbstractMessage> {

    protected AbstractMessageRouter<T> messageRouter;

    public AbstractMessageConsumer(AbstractMessageRouter<T> messageRouter) {
        this.messageRouter = messageRouter;
    }

    public abstract void consume(ConsumerRecord<String, ImsPacket<T>> packet, Acknowledgment ack);
}
