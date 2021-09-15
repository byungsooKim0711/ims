package org.kimbs.ims.channel.kakao.consumer;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.channel.kakao.service.BtMessageService;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;


@Slf4j
@Component
public class BtMessageConsumer extends AbstractMessageConsumer<BtMessageReq> {

    private final BtMessageService messageService;

    private Disposable disposable;

    public BtMessageConsumer(ReactiveKafkaConsumerTemplate<String, ImsPacket<BtMessageReq>> consumer, BtMessageService messageService) {
        super(consumer);
        this.messageService = messageService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void consume() {
        disposable = consumer.receive()
                .doOnNext(packet -> {
                    ImsPacket<BtMessageReq> message = packet.value();
                    String topic = packet.topic();
                    int partition = packet.partition();
                    long offset = packet.offset();
                    log.info("[command: {}] topic: {}, partition: {}, offset: {}, traceId: {}", message.getCommand(), topic, partition, offset, message.getTraceInfo().getTrackingId());
                })
                .doOnNext(packet -> messageService.sendMessage(packet.value()))
                .subscribe(packet -> packet.receiverOffset().acknowledge());
    }


    @Override
    public void destroy() throws Exception {
        disposable.dispose();
    }
}
