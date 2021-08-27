package org.kimbs.ims.store.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.push.PushMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class PuHistoryConsumer extends AbstractStoreConsumer<PushMessageReq> {

    @Override
    public void consume(ConsumerRecord<String, ImsPacket<PushMessageReq>> packet, Acknowledgment ack) {

    }
}
