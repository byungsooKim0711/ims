package org.kimbs.ims.router.service;

import org.kimbs.ims.model.push.PushMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.springframework.stereotype.Service;

@Service
public class PuMessageRouter extends AbstractMessageRouter<PushMessageReq> {

    @Override
    protected void getSendTopic(PushMessageReq messagePacket) {

    }

    @Override
    protected void send(PushMessageReq messagePacket) {

    }

    @Override
    protected void log(PushMessageReq messagePacket) {

    }

    @Override
    protected ImsAnalyzeLog analyzeLog(PushMessageReq messagePacket) {
        return null;
    }
}
