package org.kimbs.ims.store.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class BtHistoryConsumer extends AbstractStoreConsumer<BtMessageReq> {

    @Override
    public void consume(ConsumerRecord<String, ImsPacket<BtMessageReq>> packet, Acknowledgment ack) {

    }
}
