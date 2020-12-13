package org.kimbs.ims.router.service;

import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.springframework.stereotype.Service;

@Service
public class AtMessageRouter extends AbstractMessageRouter<AtMessageReq> {

    @Override
    protected ImsAnalyzeLog commonLog(AtMessageReq message) {
        ImsAnalyzeLog log = new ImsAnalyzeLog();

        return log;
    }
}
