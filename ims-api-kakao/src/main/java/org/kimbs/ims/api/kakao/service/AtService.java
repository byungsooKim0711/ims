package org.kimbs.ims.api.kakao.service;

import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.v1.ImsBizAtReq;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AtService extends AbstractImsService<ImsBizAtReq, Object> {

    private final static int AT_MAX_LENGTH_MESSAGE = 1000;

    @Override
    protected void checkMandatory(ImsBizAtReq request) {
        String contents = request.getContents();
        String appUserId = request.getAppUserId();
        String phoneNumber = request.getPhoneNumber();

        StringBuilder builder = new StringBuilder();
        if (StringUtils.isEmpty(contents)) {
            builder.append("empty contents");
        }
    }

    @Override
    protected void checkLength(ImsBizAtReq request) {
        if (request.getContents().length() > AT_MAX_LENGTH_MESSAGE) {
            throw new ImsTooLongMessageException("Too long message. " + request.getContents().length());
        }
    }
}
