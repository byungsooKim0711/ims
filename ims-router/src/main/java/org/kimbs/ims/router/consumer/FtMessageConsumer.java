package org.kimbs.ims.router.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.ImsPacketCommand;
import org.kimbs.ims.protocol.v1.trace.TraceInfo;
import org.kimbs.ims.router.service.FtMessageRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FtMessageConsumer extends AbstractMessageConsumer<FtMessageReq> {

    private final ObjectMapper mapper;
    private final FtMessageRouter router;

    @KafkaListener(topics = "#{routerConfig.topics.recvFt}")
    @Override
    public void consume(ImsPacket<FtMessageReq> messagePacket, Acknowledgment ack) {

        ImsPacketCommand command = messagePacket.getCommand();
        FtMessageReq ftMessageReq = this.convert(messagePacket.getData());
        TraceInfo trace = messagePacket.getTraceInfo();

        log.info("command: {}", command);
        log.info("data: {}", ftMessageReq);
        log.info("trace: {}", trace);


        ack.acknowledge();
    }

    @Override
    public FtMessageReq convert(Object data) {
        return mapper.convertValue(data, new TypeReference<FtMessageReq>() {});
    }
}
