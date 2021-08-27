package org.kimbs.ims.store.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class FtHistoryConsumer extends AbstractStoreConsumer<FtMessageReq> {

    @Override
    public void consume(ConsumerRecord<String, ImsPacket<FtMessageReq>> packet, Acknowledgment ack) {

    }
}
