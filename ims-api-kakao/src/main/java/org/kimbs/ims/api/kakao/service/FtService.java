package org.kimbs.ims.api.kakao.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.v1.ImsBizFtReq;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class FtService extends AbstractImsService<ImsBizFtReq, FtMessageReq> {

    private final static int FT_MAX_LENGTH_MESSAGE = 1000;

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
        if (request.getContents().length() > FT_MAX_LENGTH_MESSAGE) {
            throw new ImsTooLongMessageException("Too long message. " + request.getContents().length());
        }
    }

    @Override
    protected void onException(ImsBizFtReq request, Exception e) {
        log.error("exception occurred. msgUid: {}, senderKey: {}, phoneNumber: {}", request.getMsgUid(), request.getSenderKey(), request.getPhoneNumber(), e);
    }
}
