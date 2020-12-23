package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.service.cache.ImsServiceKeyCache;
import org.kimbs.ims.exception.ImsKafkaSendException;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.ImsServiceKeyException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.model.kakao.Attachment;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.model.kakao.KakaoMessageType;
import org.kimbs.ims.protocol.TraceInfo;
import org.kimbs.ims.protocol.v1.ImsBizFtReq;
import org.kimbs.ims.util.RoundRobinUtils;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class FtService extends AbstractImsService<ImsBizFtReq, FtMessageReq> {

    private final static int FT_MAX_LENGTH_MESSAGE = 1000;

    private final ImsServiceKeyCache imsServiceKeyCache;
    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public FtService(ImsServiceKeyCache imsServiceKeyCache, ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
        this.imsServiceKeyCache = imsServiceKeyCache;
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @Override
    protected void checkServiceKey(String serviceKey, FtMessageReq ftMessageReq) throws ImsServiceKeyException {
        Long userId = imsServiceKeyCache.findServiceKey(serviceKey);
        ftMessageReq.getTrace().setUserId(userId);
    }

    @Override
    protected void checkSenderKeyAndTemplate(FtMessageReq ftMessageReq) {

    }

    @Override
    protected void checkMandatory(FtMessageReq ftMessageReq) {
        String contents = ftMessageReq.getMessage();
        String appUserId = ftMessageReq.getAppUserId();
        String phoneNumber = ftMessageReq.getPhoneNumber();

        if (!StringUtils.hasText(contents)) {
            throw new ImsMandatoryException("contents is empty.");
        }

        if (!StringUtils.hasText(appUserId) && !StringUtils.hasText(phoneNumber)) {
            throw new ImsMandatoryException("appUserId and phoneNumber is empty.");
        }
    }

    @Override
    protected void checkLength(FtMessageReq ftMessageReq) {
        String message = ftMessageReq.getMessage();

        if (StringUtils.hasText(message) && message.length() > FT_MAX_LENGTH_MESSAGE) {
            throw new ImsTooLongMessageException("Too long message. " + message.length());
        }
    }

    @Override
    protected void checkDuplicateMsgUid(FtMessageReq message) {
        LocalDate now = LocalDate.now();
        Mono<Long> ret;
        String msgUid = message.getTrace().getMsgUid();
        long userId = message.getTrace().getUserId();
        KakaoMessageType type = KakaoMessageType.FT;


        StringBuilder builder = new StringBuilder();
        builder.append(type)
                .append(":")
                .append(now.format(DateTimeFormatter.ofPattern("yyMMdd")))
                .append(":")
                .append(userId);

        ret = reactiveRedisTemplate.opsForSet().add(builder.toString(), msgUid);
    }

    @Override
    protected FtMessageReq convert(ImsBizFtReq request) {
        KakaoMessageType messageType = KakaoMessageType.FT;
        String senderKey = request.getSenderKey();
        String phoneNumber = request.getPhoneNumber();
        String appUserId = request.getAppUserId();
        String userKey = request.getUserKey();
        String contents = request.getContents();
        String adFlag = request.getAdFlag();
        String wide = "W".equals(request.getFtType()) ? "Y" : "N";

        String billCode = request.getBillCode();
        String msgUid = request.getMsgUid();

        Attachment attachment = request.getAttachment();

        FtMessageReq ftMessageReq = FtMessageReq.builder()
                .messageType(messageType)
                .senderKey(senderKey)
                .phoneNumber(phoneNumber)
                .appUserId(appUserId)
                .userKey(userKey)
                .message(contents)
                .adFlag(adFlag)
                .wide(wide)
                .attachment(attachment)
                .build();

        TraceInfo traceInfo = request.getTrace();
        ftMessageReq.setTrace(traceInfo);
        ftMessageReq.getTrace().setBillCode(billCode);
        ftMessageReq.getTrace().setMsgUid(msgUid);

        return ftMessageReq;
    }

    @Override
    protected void send(FtMessageReq message) {
        List<String> ftTopics = config.getTopics().getRecvFt();
        try {
            super.sendToKafka(RoundRobinUtils.getRoundRobinValue(RoundRobinUtils.RoundRobinKey.RECV_FT, ftTopics), message);
        } catch (JsonProcessingException e) {
            throw new ImsKafkaSendException(e);
        }
    }

    @Override
    protected void onException(ImsBizFtReq request, Exception e) {
        log.error("exception occurred({}). msgUid: {}, senderKey: {}, phoneNumber: {}",
                e.getMessage(), request.getMsgUid(), request.getSenderKey(), request.getPhoneNumber());
    }

    @Override
    protected void log(FtMessageReq message) {
        TraceInfo traceInfo = message.getTrace();
        log.info("msgUid: {}, userId: {}, senderKey: {}, phoneNumber: {}",
                traceInfo.getMsgUid(), traceInfo.getUserId(), message.getSenderKey(), message.getPhoneNumber());
    }
}
