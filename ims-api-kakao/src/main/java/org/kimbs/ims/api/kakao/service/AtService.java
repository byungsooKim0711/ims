package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.kimbs.ims.api.kakao.service.cache.ImsServiceKeyCache;
import org.kimbs.ims.exception.ImsKafkaSendException;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.ImsServiceKeyException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.model.kakao.Attachment;
import org.kimbs.ims.model.kakao.KakaoMessageType;
import org.kimbs.ims.model.kakao.Supplement;
import org.kimbs.ims.protocol.TraceInfo;
import org.kimbs.ims.protocol.v1.ImsBizAtReq;
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
public class AtService extends AbstractImsService<ImsBizAtReq, AtMessageReq> {

    private final static int AT_MAX_LENGTH_MESSAGE = 1000;

    private final ImsServiceKeyCache imsServiceKeyCache;
    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public AtService(ImsServiceKeyCache imsServiceKeyCache, ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
        this.imsServiceKeyCache = imsServiceKeyCache;
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @Override
    protected void checkServiceKey(String serviceKey, AtMessageReq atMessageReq) throws ImsServiceKeyException {
        Long userId = imsServiceKeyCache.findServiceKey(serviceKey);
        atMessageReq.getTrace().setUserId(userId);
    }

    @Override
    protected void checkSenderKeyAndTemplate(AtMessageReq atMessageReq) {
//        redis -> findTemplate
//        request.getSenderKey(), request.getTemplateCode()
    }

    @Override
    protected void checkMandatory(AtMessageReq atMessageReq) {
        String message = atMessageReq.getMessage();
        String appUserId = atMessageReq.getAppUserId();
        String phoneNumber = atMessageReq.getPhoneNumber();

        if (!StringUtils.hasText(message)) {
            throw new ImsMandatoryException("contents is empty.");
        }

        if (!StringUtils.hasText(appUserId) && !StringUtils.hasText(phoneNumber)) {
            throw new ImsMandatoryException("appUserId and phoneNumber is empty.");
        }
    }

    @Override
    protected void checkLength(AtMessageReq atMessageReq) {
        String message = atMessageReq.getMessage();

        if (StringUtils.hasText(message) && message.length() > AT_MAX_LENGTH_MESSAGE) {
            throw new ImsTooLongMessageException("Too long message. " + message.length());
        }
    }

    @Override
    protected void checkDuplicateMsgUid(AtMessageReq atMessageReq) {
        LocalDate now = LocalDate.now();
        Mono<Long> ret;
        String msgUid = atMessageReq.getTrace().getMsgUid();
        long userId = atMessageReq.getTrace().getUserId();
        KakaoMessageType type = KakaoMessageType.AT;


        StringBuilder builder = new StringBuilder();
        builder.append(type)
                .append(":")
                .append(now.format(DateTimeFormatter.ofPattern("yyMMdd")))
                .append(":")
                .append(userId);

        ret = reactiveRedisTemplate.opsForSet().add(builder.toString(), msgUid);

        // 여기에 중복체크는 어떻게하는건가?
        // ret == 0 이면 이미 key 존재
    }

    @Override
    protected AtMessageReq convert(ImsBizAtReq request) {
        KakaoMessageType messageType = KakaoMessageType.AT;
        String senderKey = request.getSenderKey();
        String templateCode = request.getTemplateCode();
        String message = request.getContents();
        String phoneNumber = request.getPhoneNumber();
        String appUserId = request.getAppUserId();
        String title = request.getTitle();

        String billCode = request.getBillCode();
        String msgUid = request.getMsgUid();

        Attachment attachment = request.getAttachment();
        Supplement supplement = request.getSupplement();

        AtMessageReq atMessageReq = AtMessageReq.builder()
                .messageType(messageType)
                .senderKey(senderKey)
                .templateCode(templateCode)
                .message(message)
                .phoneNumber(phoneNumber)
                .appUserId(appUserId)
                .title(title)
                .attachment(attachment)
                .supplement(supplement)
                .build();

        TraceInfo traceInfo = request.getTrace();
        atMessageReq.setTrace(traceInfo);
        atMessageReq.getTrace().setBillCode(billCode);
        atMessageReq.getTrace().setMsgUid(msgUid);

        return atMessageReq;
    }

    @Override
    protected void send(AtMessageReq atMessageReq) {
        List<String> atTopics = config.getTopics().getRecvAt();
        try {
            super.sendToKafka(RoundRobinUtils.getRoundRobinValue(RoundRobinUtils.RoundRobinKey.RECV_AT, atTopics), atMessageReq);
        } catch (JsonProcessingException e) {
            throw new ImsKafkaSendException(e);
        }
    }

    @Override
    protected void onException(ImsBizAtReq request, Exception e) {
        log.error("exception occurred({}). msgUid: {}, senderKey: {}, phoneNumber: {}",
                e.getMessage(), request.getMsgUid(), request.getSenderKey(), request.getPhoneNumber());
    }

    @Override
    protected void log(AtMessageReq atMessageReq, RecordMetadata metadata) {
        TraceInfo traceInfo = atMessageReq.getTrace();
        log.info("topic: {}-{}@{}, msgUid: {}, userId: {}, senderKey: {}, phoneNumber: {}, templateCode: {}",
                metadata.topic(), metadata.partition(), metadata.offset(), traceInfo.getMsgUid(), traceInfo.getUserId(), atMessageReq.getSenderKey(), atMessageReq.getPhoneNumber(), atMessageReq.getTemplateCode());
    }
}
