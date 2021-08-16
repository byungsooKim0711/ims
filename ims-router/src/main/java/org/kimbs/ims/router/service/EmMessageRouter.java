package org.kimbs.ims.router.service;

import org.kimbs.ims.model.email.EmailMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.springframework.stereotype.Service;

@Service
public class EmMessageRouter extends AbstractMessageRouter<EmailMessageReq> {

    @Override
    protected void getSendTopic(EmailMessageReq messagePacket) {

    }

    @Override
    protected void send(EmailMessageReq messagePacket) {

    }

    @Override
    protected void log(EmailMessageReq messagePacket) {

    }

    @Override
    protected ImsAnalyzeLog analyzeLog(EmailMessageReq messagePacket) {
        return null;
    }
}
