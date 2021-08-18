package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsKafkaSendException;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.model.kakao.KakaoMessageType;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.ImsPacketCommand;
import org.kimbs.ims.protocol.v1.kakao.ft.ImsBizFtReq;
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
public class FtService extends AbstractKakaoService<ImsBizFtReq, ImsPacket<FtMessageReq>> {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

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

        return ImsPacket.<FtMessageReq>builder()
                .command(ImsPacketCommand.RECEIVE_FT)
                .data(ftMessageReq)
                .traceInfo(traceInfo)
                .build();
    }

    @Override
    protected void checkSenderKeyAndTemplate(ImsPacket<FtMessageReq> message) {

    }

    @Override
    protected void checkMandatory(ImsPacket<FtMessageReq> message) throws ImsMandatoryException {
        FtMessageReq ftMessageReq = message.getData();
        String appUserId = ftMessageReq.getAppUserId();
        String phoneNumber = ftMessageReq.getPhoneNumber();

        if (!StringUtils.hasText(appUserId) && !StringUtils.hasText(phoneNumber)) {
            throw new ImsMandatoryException("appUserId and phoneNumber is empty.");
        }
    }

    @Override
    protected void checkDuplicateMsgUid(ImsPacket<FtMessageReq> message) {
        // TODO:
    }

    @Override
    protected void send(ImsPacket<FtMessageReq> message) {
        List<String> ftTopics = config.getTopics().getRecvAt();
        try {
            kafkaService.sendToKafka(RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.RECV_FT, ftTopics), message);
        } catch (JsonProcessingException e) {
            throw new ImsKafkaSendException(e);
        }
    }

    @Override
    protected void onException(ImsBizFtReq request, Exception e) {
        log.warn("exception occurred({}). messageId: {}, senderKey: {}, phoneNumber: {}",
                e.getMessage(), request.getMessageId(), request.getSenderKey(), request.getPhoneNumber());
    }

    @Override
    protected void log(ImsPacket<FtMessageReq> message) {
        FtMessageReq data = message.getData();
        TraceInfo trace = message.getTraceInfo();

        log.info("[Log] username: {}, serialNumber: {}, senderKey: {}, phoneNumber: {}",
                trace.getCustomerId(), data.getSerialNumber(), data.getSenderKey(), data.getPhoneNumber());
    }
}
