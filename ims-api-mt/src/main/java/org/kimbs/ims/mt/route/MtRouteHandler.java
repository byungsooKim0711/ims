package org.kimbs.ims.mt.route;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.mt.MtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.ImsPacketCommand;
import org.kimbs.ims.protocol.v1.mt.ImsMtReq;
import org.kimbs.ims.protocol.v1.trace.TraceInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Component
public class MtRouteHandler extends AbstractRouteHandler<ImsMtReq, ImsPacket<MtMessageReq>> {

    public MtRouteHandler(Validator validator) {
        super(validator);
    }

    @Override
    protected void validation(ImsMtReq request) {
        Set<ConstraintViolation<ImsMtReq>> constraint = validator.validate(request);
    }

    @Override
    protected ImsPacket<MtMessageReq> convert(ImsMtReq request) {
        MtMessageReq mtMessageReq = MtMessageReq.builder()
                .build();

        TraceInfo traceInfo = new TraceInfo();
        traceInfo.setMessageId(request.getMessageId());
        traceInfo.setReceivedAt(LocalDateTime.now());

        return ImsPacket.<MtMessageReq>builder()
                .command(ImsPacketCommand.RECEIVE_MT)
                .data(mtMessageReq)
                .traceInfo(traceInfo)
                .build();
    }

    @Override
    protected void checkMandatory(ImsPacket<MtMessageReq> message) {

    }

    @Override
    protected void checkDuplicateMsgUid(ImsPacket<MtMessageReq> message) {

    }

    @Override
    protected void send(ImsPacket<MtMessageReq> message) {

    }

    @Override
    protected Mono<ServerResponse> onException(Throwable e) {
        return null;
    }

    @Override
    protected void log(ImsPacket<MtMessageReq> message) {

    }
}
