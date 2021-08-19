package org.kimbs.ims.channel.kakao.consumer;

import org.kimbs.ims.protocol.AbstractMessage;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.support.Acknowledgment;

public abstract class AbstractMessageConsumer<T extends AbstractMessage> {

    public abstract void consume(ImsPacket<T> packet, Acknowledgment ack);
}
