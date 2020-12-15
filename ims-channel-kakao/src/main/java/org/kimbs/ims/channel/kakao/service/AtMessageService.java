package org.kimbs.ims.channel.kakao.service;

import org.kimbs.ims.model.kakao.AtMessageReq;
import org.springframework.stereotype.Service;

@Service
public class AtMessageService extends AbstractMessageService<AtMessageReq> {

    @Override
    protected void request(AtMessageReq message) {

    }

    @Override
    protected void report(AtMessageReq message) {

    }

    @Override
    protected void log(AtMessageReq message) {

    }
}
