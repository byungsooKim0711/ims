package org.kimbs.ims.router.service;

import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.springframework.stereotype.Service;

@Service
public class BtMessageRouter extends AbstractMessageRouter<BtMessageReq> {

    @Override
    protected ImsAnalyzeLog commonLog(BtMessageReq message) {
        return null;
    }
}
