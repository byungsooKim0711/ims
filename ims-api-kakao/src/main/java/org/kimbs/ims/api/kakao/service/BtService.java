package org.kimbs.ims.api.kakao.service;

import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.v1.ImsBizBtReq;
import org.springframework.stereotype.Service;

@Service
public class BtService extends AbstractImsService<ImsBizBtReq, Object> {

    private final static int BT_MAX_LENGTH_MESSAGE = 1000;

    @Override
    protected void checkLength(ImsBizBtReq request) {
        if (request.getContents().length() > BT_MAX_LENGTH_MESSAGE) {
            throw new ImsTooLongMessageException("Too long message. " + request.getContents().length());
        }
    }
}
