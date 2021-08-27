package org.kimbs.ims.store.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.email.EmailMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class EmHistoryConsumer extends AbstractStoreConsumer<EmailMessageReq> {

    @Override
    public void consume(ConsumerRecord<String, ImsPacket<EmailMessageReq>> packet, Acknowledgment ack) {

    }
}
