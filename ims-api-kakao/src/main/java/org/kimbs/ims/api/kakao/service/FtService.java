package org.kimbs.ims.api.kakao.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.model.kakao.Attachment;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.model.kakao.KakaoMessageType;
import org.kimbs.ims.protocol.TraceInfo;
import org.kimbs.ims.protocol.v1.ImsBizFtReq;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class FtService extends AbstractImsService<ImsBizFtReq, FtMessageReq> {

    private final static int FT_MAX_LENGTH_MESSAGE = 1000;

    @Override
    protected void checkSenderKeyAndTemplate(ImsBizFtReq request) {

    }

    @Override
    protected void checkMandatory(ImsBizFtReq request) {
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
    protected void checkLength(ImsBizFtReq request) {
        String message = request.getContents();

        if (StringUtils.hasText(message) && message.length() > FT_MAX_LENGTH_MESSAGE) {
            throw new ImsTooLongMessageException("Too long message. " + request.getContents().length());
        }
    }

    @Override
    protected void checkDuplicateMsgUid(ImsBizFtReq request) {

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


        ftMessageReq.addTraceInfo(TraceInfo.BILL_CODE, billCode);
        ftMessageReq.addTraceInfo(TraceInfo.MSG_UID, msgUid);

        return ftMessageReq;
    }

    @Override
    protected void send(FtMessageReq message) {
//        super.sendToKafka();
    }

    @Override
    protected void onException(ImsBizFtReq request, Exception e) {
        log.error("exception occurred({}). msgUid: {}, senderKey: {}, phoneNumber: {}", e.getMessage(), request.getMsgUid(), request.getSenderKey(), request.getPhoneNumber());
    }
}
