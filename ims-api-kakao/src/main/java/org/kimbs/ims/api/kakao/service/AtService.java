package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class AtService extends AbstractImsService<ImsBizAtReq, AtMessageReq> {

    private final static int AT_MAX_LENGTH_MESSAGE = 1000;

    private final ImsServiceKeyCache imsServiceKeyCache;

    public AtService(ImsServiceKeyCache imsServiceKeyCache) {
        this.imsServiceKeyCache = imsServiceKeyCache;
    }

    @Override
    protected void checkServiceKey(String serviceKey, ImsBizAtReq request) throws ImsServiceKeyException {
        Long userId = imsServiceKeyCache.findServiceKey(serviceKey);
        request.addTraceInfo(TraceInfo.USER_ID, userId);
    }

    @Override
    protected void checkSenderKeyAndTemplate(ImsBizAtReq request) {
//        redis -> findTemplate
//        request.getSenderKey(), request.getTemplateCode()
    }

    @Override
    protected void checkMandatory(ImsBizAtReq request) {
        String contents = request.getContents();
        String appUserId = request.getAppUserId();
        String phoneNumber = request.getPhoneNumber();

        if (!StringUtils.hasText(contents)) {
            throw new ImsMandatoryException("contents is empty.");
        }

        if (!StringUtils.hasText(appUserId) && !StringUtils.hasText(phoneNumber)) {
            throw new ImsMandatoryException("appUserId and phoneNumber is empty.");
        }
    }

    @Override
    protected void checkLength(ImsBizAtReq request) {
        String message = request.getContents();

        if (StringUtils.hasText(message) && message.length() > AT_MAX_LENGTH_MESSAGE) {
            throw new ImsTooLongMessageException("Too long message. " + request.getContents().length());
        }
    }

    @Override
    protected void checkDuplicateMsgUid(ImsBizAtReq request) {

    }

    @Override
    protected AtMessageReq convert(ImsBizAtReq request) {
        KakaoMessageType messageType = KakaoMessageType.AT;
        String senderKey = request.getSenderKey();
        String templateCode = request.getTemplateCode();
        String message = request.getContents();
        String phoneNumber = request.getPhoneNumber();
        String appUserId = request.getAppUserId();
        String title = request.getTemplateTitle();

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

        atMessageReq.addTraceInfo(TraceInfo.BILL_CODE, billCode);
        atMessageReq.addTraceInfo(TraceInfo.MSG_UID, msgUid);

        return atMessageReq;
    }

    @Override
    protected void send(AtMessageReq message) {
        List<String> atTopics = config.getTopics().getRecvAt();
        try {
            super.sendToKafka(RoundRobinUtils.getRoundRobinValue(RoundRobinUtils.RoundRobinKey.RECV_AT, atTopics), message);
        } catch (JsonProcessingException e) {
            throw new ImsKafkaSendException(e);
        }
    }

    @Override
    protected void onException(ImsBizAtReq request, Exception e) {
        log.error("exception occurred({}). msgUid: {}, senderKey: {}, phoneNumber: {}", e.getMessage(), request.getMsgUid(), request.getSenderKey(), request.getPhoneNumber());
    }
}
