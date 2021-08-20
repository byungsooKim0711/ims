package org.kimbs.ims.router.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.ImsPacketCommand;
import org.kimbs.ims.util.RoundRobinUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FtMessageRouter extends AbstractMessageRouter<FtMessageReq> {

    @Override
    protected void convertData(ImsPacket<FtMessageReq> packet) {
        packet.updateData(mapper.convertValue(packet.getData(), new TypeReference<FtMessageReq>() {}));
    }

    @Override
    protected void getSendTopic(ImsPacket<FtMessageReq> packet) {
        List<String> defaultSendTopicList = config.getTopics().getSendFt();
        String destinationTopic = RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.SEND_FT, defaultSendTopicList);

        packet.getTraceInfo().setDestinationTopic(destinationTopic);
    }

    @Override
    protected void send(ImsPacket<FtMessageReq> packet) {
        packet.updateCommand(ImsPacketCommand.SEND_FT);

        kafkaService.sendToKafka(packet.getTraceInfo().getDestinationTopic(), packet);
    }

    @Override
    protected void log(ImsPacket<FtMessageReq> packet) {
        log.info("[FT-LOG] command: {}, trackingId: {}, serialNumber: {}", packet.getCommand(), packet.getTraceInfo().getTrackingId(), packet.getData().getSerialNumber());
    }

    @Override
    protected ImsAnalyzeLog analyzeLog(ImsPacket<FtMessageReq> packet) {
        FtMessageReq data = packet.getData();
        ImsAnalyzeLog log = new ImsAnalyzeLog();
        log.setMessage(data.getMessage());
        log.setPhoneNumber(data.getPhoneNumber());
        log.setSerialNumber(data.getSerialNumber());

        return log;
    }
}
