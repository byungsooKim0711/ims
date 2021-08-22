package org.kimbs.ims.router.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.push.PushMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PuMessageRouter extends AbstractMessageRouter<PushMessageReq> {

    @Override
    protected void getSendTopic(ImsPacket<PushMessageReq> packet) {

    }

    @Override
    protected void send(ImsPacket<PushMessageReq> packet) {

    }

    @Override
    protected void log(ImsPacket<PushMessageReq> packet) {
        log.info("[PU-LOG] command: {}, trackingId: {}, serialNumber: {}", packet.getCommand(), packet.getTraceInfo().getTrackingId(), packet.getData().getSerialNumber());
    }

    @Override
    protected ImsAnalyzeLog analyzeLog(ImsPacket<PushMessageReq> packet) {
        ImsAnalyzeLog log = new ImsAnalyzeLog();
        return log;
    }
}
