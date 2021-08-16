package org.kimbs.ims.api.kakao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.v1.kakao.bt.ImsBizBtReq;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BtService extends AbstractKakaoService<ImsBizBtReq, ImsPacket<BtMessageReq>> {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;


    @Override
    protected ImsPacket<BtMessageReq> convert(ImsBizBtReq request) {
        return null;
    }

    @Override
    protected void checkSenderKeyAndTemplate(ImsPacket<BtMessageReq> message) {

    }

    @Override
    protected void checkMandatory(ImsPacket<BtMessageReq> message) throws ImsMandatoryException {

    }

    @Override
    protected void checkDuplicateMsgUid(ImsPacket<BtMessageReq> message) {

    }

    @Override
    protected void send(ImsPacket<BtMessageReq> message) {

    }

    @Override
    protected void onException(ImsBizBtReq request, Exception e) {

    }

    @Override
    protected void log(ImsPacket<BtMessageReq> message) {

    }
}
