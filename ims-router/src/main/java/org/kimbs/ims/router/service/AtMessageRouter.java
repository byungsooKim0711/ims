package org.kimbs.ims.router.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.ImsPacketCommand;
import org.kimbs.ims.util.RoundRobinUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AtMessageRouter extends AbstractMessageRouter<AtMessageReq> {

    @Override
    protected void convertData(ImsPacket<AtMessageReq> packet) {
        packet.updateData(mapper.convertValue(packet.getData(), new TypeReference<AtMessageReq>() {}));
    }

    @Override
    protected void getSendTopic(ImsPacket<AtMessageReq> packet) {
        List<String> defaultSendTopicList = config.getTopics().getSendAt();
        String destinationTopic = RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.SEND_AT, defaultSendTopicList);

        packet.getTraceInfo().setDestinationTopic(destinationTopic);
    }

    @Override
    protected void send(ImsPacket<AtMessageReq> packet) {
        packet.updateCommand(ImsPacketCommand.SEND_AT);
//        try {
//            super.sendToKafka(message.getTrace().getDestinationTopic(), packet);
//        } catch (JsonProcessingException e) {
//            log.error("json parse error. serialNumber: {}, message: {}", packet.getSerialNumber(), packet, e);
//            throw new ImsKafkaSendException(e);
//        } catch (Exception e) {
//            log.error("exception occurred({}). serialNumber: {}", e.getMessage(), packet.getSerialNumber(), e);
//            throw new ImsKafkaSendException(e);
//        }

    }

    @Override
    protected void log(ImsPacket<AtMessageReq> packet) {
        log.info("[AT-LOG] command: {}, trackingId: {}, serialNumber: {}", packet.getCommand(), packet.getTraceInfo().getTrackingId(), packet.getData().getSerialNumber());
    }

    @Override
    protected ImsAnalyzeLog analyzeLog(ImsPacket<AtMessageReq> packet) {
        AtMessageReq data = packet.getData();
        ImsAnalyzeLog log = new ImsAnalyzeLog();
        log.setMessage(data.getMessage());
        log.setPhoneNumber(data.getPhoneNumber());
        log.setSerialNumber(data.getSerialNumber());

        return log;
    }
}
