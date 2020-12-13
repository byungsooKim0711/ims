package org.kimbs.ims.router.service;

import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.springframework.stereotype.Service;

@Service
public class FtMessageRouter extends AbstractMessageRouter<FtMessageReq> {

    @Override
    protected ImsAnalyzeLog commonLog(FtMessageReq message) {
        return null;
    }
}
