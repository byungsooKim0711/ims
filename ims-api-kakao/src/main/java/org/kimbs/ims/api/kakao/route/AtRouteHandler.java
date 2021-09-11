package org.kimbs.ims.api.kakao.route;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.config.ApiKakaoConfig;
import org.kimbs.ims.api.kakao.service.KafkaService;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.NotSupportMessageType;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.model.kakao.KakaoMessageType;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.ImsPacketCommand;
import org.kimbs.ims.protocol.v1.kakao.at.ImsBizAtReq;
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
public class AtRouteHandler extends AbstractRouteHandler<ImsBizAtReq, AtMessageReq> {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public AtRouteHandler(Validator validator, ApiKakaoConfig config, KafkaService kafkaService, ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
        super(validator, config, kafkaService);
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @Override
    protected ImsPacket<AtMessageReq> convert(ImsBizAtReq request) {
        KakaoMessageType messageType = KakaoMessageType.fromValue(request.getMessageType());
        String messageId = request.getMessageId();

        AtMessageReq atMessageReq = AtMessageReq.builder()
                .serialNumber(SerialNumberUtil.generateSerialNumber(messageType.getType(), messageId))
                .messageType(messageType)
                .senderKey(request.getSenderKey())
                .templateCode(request.getTemplateCode())
                .message(request.getMessage())
                .phoneNumber(request.getPhoneNumber())
                .appUserId(request.getAppUserId())
                .title(request.getTitle())
                .attachment(request.getAttachment())
                .supplement(request.getSupplement())
                .header(request.getHeader())
                .build();

        TraceInfo traceInfo = new TraceInfo();
        traceInfo.setMessageId(request.getMessageId());
        traceInfo.setReceivedAt(LocalDateTime.now());
        traceInfo.setMapping(request.getMapping());

        return ImsPacket.<AtMessageReq>builder()
                .command(ImsPacketCommand.RECEIVE_AT)
                .data(atMessageReq)
                .traceInfo(traceInfo)
                .build();
    }

    @Override
    protected void checkMandatory(ImsPacket<AtMessageReq> message) throws ImsMandatoryException {
        AtMessageReq atMessageReq = message.getData();
        String appUserId = atMessageReq.getAppUserId();
        String phoneNumber = atMessageReq.getPhoneNumber();

        KakaoMessageType requestType = atMessageReq.getMessageType();

        switch (requestType) {
            case AT:
            case AI:
                break;
            default:
                throw new NotSupportMessageType("Not support message_type : " + requestType);
        }

        if (!StringUtils.hasText(appUserId) && !StringUtils.hasText(phoneNumber)) {
            throw new ImsMandatoryException("appUserId and phoneNumber is empty.");
        }
    }

    @Override
    protected void checkSenderKeyAndTemplate(ImsPacket<AtMessageReq> message) {

    }

    @Override
    protected void checkDuplicateMsgUid(ImsPacket<AtMessageReq> message) {

    }

    @Override
    protected void send(ImsPacket<AtMessageReq> message) {
        List<String> atTopics = config.getTopics().getRecvAt();

        kafkaService.sendToKafka(RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.RECV_AT, atTopics), message);
    }

    @Override
    protected void log(ImsPacket<AtMessageReq> message) {
        AtMessageReq data = message.getData();
        TraceInfo trace = message.getTraceInfo();

        log.info("[Log] username: {}, serialNumber: {}, senderKey: {}, templateCode: {}, phoneNumber: {}",
                trace.getCustomerId(), data.getSerialNumber(), data.getSenderKey(), data.getTemplateCode(), data.getPhoneNumber());
    }
}
