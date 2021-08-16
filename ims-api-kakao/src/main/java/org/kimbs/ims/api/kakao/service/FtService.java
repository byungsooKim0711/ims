package org.kimbs.ims.api.kakao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.v1.kakao.ft.ImsBizFtReq;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FtService extends AbstractKakaoService<ImsBizFtReq, ImsPacket<FtMessageReq>> {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    @Override
    protected ImsPacket<FtMessageReq> convert(ImsBizFtReq request) {
        return null;
    }

    @Override
    protected void checkSenderKeyAndTemplate(ImsPacket<FtMessageReq> message) {

    }

    @Override
    protected void checkMandatory(ImsPacket<FtMessageReq> message) throws ImsMandatoryException {

    }

    @Override
    protected void checkDuplicateMsgUid(ImsPacket<FtMessageReq> message) {

    }

    @Override
    protected void send(ImsPacket<FtMessageReq> message) {

    }

    @Override
    protected void onException(ImsBizFtReq request, Exception e) {

    }

    @Override
    protected void log(ImsPacket<FtMessageReq> message) {

    }
}
