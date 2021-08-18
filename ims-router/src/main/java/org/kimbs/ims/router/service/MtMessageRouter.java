package org.kimbs.ims.router.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.mt.MtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.protocol.ImsPacket;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MtMessageRouter extends AbstractMessageRouter<MtMessageReq> {

    @Override
    protected void convertData(ImsPacket<MtMessageReq> packet) {
        packet.updateData(mapper.convertValue(packet.getData(), new TypeReference<MtMessageReq>() {}));
    }

    @Override
    protected void getSendTopic(ImsPacket<MtMessageReq> packet) {

    }

    @Override
    protected void send(ImsPacket<MtMessageReq> packet) {

    }

    @Override
    protected void log(ImsPacket<MtMessageReq> packet) {
        log.info("[MT-LOG] command: {}, trackingId: {}, serialNumber: {}", packet.getCommand(), packet.getTraceInfo().getTrackingId(), packet.getData().getSerialNumber());
    }

    @Override
    protected ImsAnalyzeLog analyzeLog(ImsPacket<MtMessageReq> messagePacket) {
        ImsAnalyzeLog log = new ImsAnalyzeLog();
        return log;
    }
}
