package org.kimbs.ims.api.kakao.route;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.config.ApiKakaoConfig;
import org.kimbs.ims.api.kakao.service.KafkaService;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.v1.kakao.bt.ImsBizBtReq;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Slf4j
@Component
public class BtRouteHandler extends AbstractRouteHandler<ImsBizBtReq, BtMessageReq> {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public BtRouteHandler(Validator validator, ApiKakaoConfig config, KafkaService kafkaService, ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
        super(validator, config, kafkaService);
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @Override
    protected ImsPacket<BtMessageReq> convert(ImsBizBtReq request) {
        return null;
    }

    @Override
    protected void checkMandatory(ImsPacket<BtMessageReq> message) throws ImsMandatoryException {

    }

    @Override
    protected void checkSenderKeyAndTemplate(ImsPacket<BtMessageReq> message) {

    }

    @Override
    protected void checkDuplicateMsgUid(ImsPacket<BtMessageReq> message) {

    }

    @Override
    protected void send(ImsPacket<BtMessageReq> message) {

    }

    @Override
    protected void log(ImsPacket<BtMessageReq> message) {

    }
}