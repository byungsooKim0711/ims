package org.kimbs.ims.store.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class AtHistoryConsumer extends AbstractStoreConsumer<AtMessageReq> {

    @Override
    public void consume(ConsumerRecord<String, ImsPacket<AtMessageReq>> packet, Acknowledgment ack) {

    }
}
