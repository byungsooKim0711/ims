package org.kimbs.ims.api.kakao.route;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.config.ApiKakaoConfig;
import org.kimbs.ims.api.kakao.service.KafkaService;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.NotSupportMessageType;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.model.kakao.KakaoMessageType;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.ImsPacketCommand;
import org.kimbs.ims.protocol.v1.kakao.ft.ImsBizFtReq;
import org.kimbs.ims.protocol.v1.trace.TraceInfo;
import org.kimbs.ims.util.RoundRobinUtil;
import org.kimbs.ims.util.SerialNumberUtil;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class FtRouteHandler extends AbstractRouteHandler<ImsBizFtReq, FtMessageReq> {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public FtRouteHandler(Validator validator, ApiKakaoConfig config, KafkaService kafkaService, ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
        super(validator, config, kafkaService);
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @Override
    protected ImsPacket<FtMessageReq> convert(ImsBizFtReq request) {
        KakaoMessageType messageType = KakaoMessageType.fromValue(request.getMessageType());
        String messageId = request.getMessageId();
        FtMessageReq ftMessageReq = FtMessageReq.builder()
                .messageType(messageType)
                .senderKey(request.getSenderKey())
                .message(request.getMessage())
                .appUserId(request.getAppUserId())
                .attachment(request.getAttachment())
                .phoneNumber(request.getPhoneNumber())
                .serialNumber(SerialNumberUtil.generateSerialNumber(messageType.getType(), messageId))
                .adFlag(request.getAdFlag())
                .userKey(request.getUserKey())
                .build();

        TraceInfo traceInfo = new TraceInfo();
        traceInfo.setMessageId(messageId);
        traceInfo.setReceivedAt(LocalDateTime.now());
        traceInfo.setMapping(request.getMapping());

        return ImsPacket.<FtMessageReq>builder()
                .command(ImsPacketCommand.RECEIVE_FT)
                .data(ftMessageReq)
                .traceInfo(traceInfo)
                .build();
    }

    @Override
    protected void checkMandatory(ImsPacket<FtMessageReq> message) throws ImsMandatoryException {
        FtMessageReq ftMessageReq = message.getData();
        String appUserId = ftMessageReq.getAppUserId();
        String phoneNumber = ftMessageReq.getPhoneNumber();

        KakaoMessageType requestType = ftMessageReq.getMessageType();

        switch (requestType) {
            case FT:
            case FI:
            case FW:
                break;
            default:
                throw new NotSupportMessageType("Not support message_type : " + requestType);
        }

        if (!StringUtils.hasText(appUserId) && !StringUtils.hasText(phoneNumber)) {
            throw new ImsMandatoryException("appUserId and phoneNumber is empty.");
        }
    }

    @Override
    protected void checkSenderKeyAndTemplate(ImsPacket<FtMessageReq> message) {

    }

    @Override
    protected void checkDuplicateMsgUid(ImsPacket<FtMessageReq> message) {

    }

    @Override
    protected void send(ImsPacket<FtMessageReq> message) {
        List<String> ftTopics = config.getTopics().getRecvFt();

        kafkaService.sendToKafka(RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.RECV_FT, ftTopics), message);
    }

    @Override
    protected void log(ImsPacket<FtMessageReq> message) {
        FtMessageReq data = message.getData();
        TraceInfo trace = message.getTraceInfo();

        log.info("[Log] username: {}, serialNumber: {}, senderKey: {}, phoneNumber: {}",
                trace.getCustomerId(), data.getSerialNumber(), data.getSenderKey(), data.getPhoneNumber());
    }
}
