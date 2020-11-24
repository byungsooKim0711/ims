package org.kimbs.ims.api.kakao.service;

import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.v1.ImsBizFtReq;
import org.springframework.stereotype.Service;

@Service
public class FtService extends AbstractImsService<ImsBizFtReq, Object> {

    private final static int FT_MAX_LENGTH_MESSAGE = 1000;

    @Override
    protected void checkLength(ImsBizFtReq request) {
        if (request.getContents().length() > FT_MAX_LENGTH_MESSAGE) {
            throw new ImsTooLongMessageException("Too long message. " + request.getContents().length());
        }
    }
}
