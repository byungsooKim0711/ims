package org.kimbs.ims.api.kakao.service;

import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.v1.ImsBizBtReq;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BtService extends AbstractImsService<ImsBizBtReq, BtMessageReq> {

    private final static int BT_MAX_LENGTH_MESSAGE = 1000;

    @Override
    protected void checkMandatory(ImsBizBtReq request) {
        String contents = request.getContents();
//        String appUserId = request.getAppUserId();
//        String phoneNumber = request.getPhoneNumber();

        if (!StringUtils.hasText(contents)) {
            throw new ImsMandatoryException("contents is empty.");
        }

//        if (!StringUtils.hasText(appUserId) && !StringUtils.hasText(phoneNumber)) {
//            throw new ImsMandatoryException("appUserId and phoneNumber is empty.");
//        }
    }

    @Override
    protected void checkLength(ImsBizBtReq request) {
        if (request.getContents().length() > BT_MAX_LENGTH_MESSAGE) {
            throw new ImsTooLongMessageException("Too long message. " + request.getContents().length());
        }
    }
}
