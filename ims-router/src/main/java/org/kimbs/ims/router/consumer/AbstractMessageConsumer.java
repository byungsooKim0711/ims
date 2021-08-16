package org.kimbs.ims.router.consumer;

import org.kimbs.ims.protocol.AbstractMessage;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.kafka.support.Acknowledgment;

public abstract class AbstractMessageConsumer<T extends AbstractMessage> {

    public abstract void consume(ImsPacket<T> messagePacket, Acknowledgment ack);
}
