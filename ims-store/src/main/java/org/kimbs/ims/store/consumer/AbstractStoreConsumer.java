package org.kimbs.ims.store.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.protocol.AbstractMessage;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.support.Acknowledgment;

public abstract class AbstractStoreConsumer<T extends AbstractMessage> {

//    protected AbstractStoreService abstractStoreService;
//
//    public AbstractStoreConsumer(AbstractStoreService abstractStoreService) {
//        this.abstractStoreService = abstractStoreService;
//    }

    public abstract void consume(ConsumerRecord<String, ImsPacket<T>> packet, Acknowledgment ack);
}
