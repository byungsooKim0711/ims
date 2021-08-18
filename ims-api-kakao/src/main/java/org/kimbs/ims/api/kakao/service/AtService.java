package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsKafkaSendException;
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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AtService extends AbstractKakaoService<ImsBizAtReq, ImsPacket<AtMessageReq>> {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

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

        return ImsPacket.<AtMessageReq>builder()
                .command(ImsPacketCommand.RECEIVE_AT)
                .data(atMessageReq)
                .traceInfo(traceInfo)
                .build();
    }

    @Override
    protected void checkSenderKeyAndTemplate(ImsPacket<AtMessageReq> request) {
//        redis -> findTemplate
//        request.getSenderKey(), request.getTemplateCode()
    }

    @Override
    protected void checkMandatory(ImsPacket<AtMessageReq> request) throws ImsMandatoryException {
        AtMessageReq atMessageReq = request.getData();
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
    protected void checkDuplicateMsgUid(ImsPacket<AtMessageReq> request) {
//        LocalDate now = LocalDate.now();
//        Mono<Long> ret;
//        AtMessageReq atMessageReq = atMessagePacket.getData();
//        String msgUid = atMessageReq.getTrace().getMessageId();
//        long userId = atMessageReq.getTrace().getCustomerId();
//        KakaoMessageType type = KakaoMessageType.AT;
//
//
//        StringBuilder builder = new StringBuilder();
//        builder.append(now.format(DateTimeFormatter.ofPattern("yyMMdd")))
//                .append(":")
//                .append(type)
//                .append(":")
//                .append(userId);
//
//        ret = reactiveRedisTemplate.opsForSet().add(builder.toString(), msgUid);

        // 여기에 중복체크는 어떻게하는건가?
        // ret == 0 이면 이미 key 존재
    }

    @Override
    protected void send(ImsPacket<AtMessageReq> message) {
        List<String> atTopics = config.getTopics().getRecvAt();
        try {
            kafkaService.sendToKafka(RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.RECV_AT, atTopics), message);
        } catch (JsonProcessingException e) {
            throw new ImsKafkaSendException(e);
        }
    }

    @Override
    protected void onException(ImsBizAtReq request, Exception e) {
        log.warn("exception occurred({}). messageId: {}, senderKey: {}, phoneNumber: {}, appUserId: {}",
                e.getMessage(), request.getMessageId(), request.getSenderKey(), request.getPhoneNumber(), request.getAppUserId());
    }

    @Override
    protected void log(ImsPacket<AtMessageReq> message) {
        AtMessageReq data = message.getData();
        TraceInfo trace = message.getTraceInfo();

        log.info("[Log] username: {}, serialNumber: {}, senderKey: {}, templateCode: {}, phoneNumber: {}",
                trace.getCustomerId(), data.getSerialNumber(), data.getSenderKey(), data.getTemplateCode(), data.getPhoneNumber());
    }
}
