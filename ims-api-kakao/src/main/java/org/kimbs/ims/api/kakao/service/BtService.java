package org.kimbs.ims.api.kakao.service;

import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.v1.ImsBizBtReq;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BtService extends AbstractImsService<ImsBizBtReq, Object> {

    private final static int BT_MAX_LENGTH_MESSAGE = 1000;

    @Override
    protected void checkMandatory(ImsBizBtReq request) {
        String contents = request.getContents();
//        String appUserId = request.getAppUserId();
//        String phoneNumber = request.getPhoneNumber();

        StringBuilder builder = new StringBuilder();
        if (StringUtils.isEmpty(contents)) {
            builder.append("empty contents");
        }
    }

    @Override
    protected void checkLength(ImsBizBtReq request) {
        if (request.getContents().length() > BT_MAX_LENGTH_MESSAGE) {
            throw new ImsTooLongMessageException("Too long message. " + request.getContents().length());
        }
    }
}
