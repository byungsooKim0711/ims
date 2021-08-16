package org.kimbs.ims.router.service;

import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.springframework.stereotype.Service;

@Service
public class MtMessageRouter extends AbstractMessageRouter<MtMessageRouter> {

    @Override
    protected void getSendTopic(MtMessageRouter messagePacket) {

    }

    @Override
    protected void send(MtMessageRouter messagePacket) {

    }

    @Override
    protected void log(MtMessageRouter messagePacket) {

    }

    @Override
    protected ImsAnalyzeLog analyzeLog(MtMessageRouter messagePacket) {
        return null;
    }
}
