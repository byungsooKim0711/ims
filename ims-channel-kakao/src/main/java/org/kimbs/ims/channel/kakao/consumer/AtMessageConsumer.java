package org.kimbs.ims.channel.kakao.consumer;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.channel.kakao.service.AtMessageService;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;

@Slf4j
@Component
public class AtMessageConsumer extends AbstractMessageConsumer<AtMessageReq> {

    private final AtMessageService messageService;

    private Disposable disposable;

    public AtMessageConsumer(ReactiveKafkaConsumerTemplate<String, ImsPacket<AtMessageReq>> consumer, AtMessageService messageService) {
        super(consumer);
        this.messageService = messageService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void consume() {
        disposable = consumer.receive()
                .doOnNext(packet -> {
                    ImsPacket<AtMessageReq> message = packet.value();
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
