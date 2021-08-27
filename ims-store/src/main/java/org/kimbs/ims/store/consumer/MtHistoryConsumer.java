package org.kimbs.ims.store.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.mt.MtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MtHistoryConsumer extends AbstractStoreConsumer<MtMessageReq> {

    @Override
    public void consume(ConsumerRecord<String, ImsPacket<MtMessageReq>> packet, Acknowledgment ack) {

    }
}
