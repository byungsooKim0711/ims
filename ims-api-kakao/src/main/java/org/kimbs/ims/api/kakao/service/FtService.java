package org.kimbs.ims.api.kakao.service;

import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.v1.ImsBizFtReq;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class FtService extends AbstractImsService<ImsBizFtReq, Object> {

    private final static int FT_MAX_LENGTH_MESSAGE = 1000;

    @Override
    protected void checkMandatory(ImsBizFtReq request) {
        String contents = request.getContents();
        String appUserId = request.getAppUserId();
        String phoneNumber = request.getPhoneNumber();

        StringBuilder builder = new StringBuilder();
        if (StringUtils.isEmpty(contents)) {
            builder.append("empty contents");
        }
    }

    @Override
    protected void checkLength(ImsBizFtReq request) {
        if (request.getContents().length() > FT_MAX_LENGTH_MESSAGE) {
            throw new ImsTooLongMessageException("Too long message. " + request.getContents().length());
        }
    }
}
