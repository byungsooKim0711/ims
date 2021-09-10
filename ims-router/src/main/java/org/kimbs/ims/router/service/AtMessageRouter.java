package org.kimbs.ims.router.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.ImsPacketCommand;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

import static org.kimbs.ims.util.RoundRobinUtil.RoundRobinKey;
import static org.kimbs.ims.util.RoundRobinUtil.getRoundRobinValue;

@Slf4j
@Service
public class AtMessageRouter extends AbstractMessageRouter<AtMessageReq> {

    @Override
    protected void getSendTopic(ImsPacket<AtMessageReq> packet) {
        List<String> defaultSendTopicList = config.getTopics().getSendAt();
        String destinationTopic = getRoundRobinValue(RoundRobinKey.SEND_AT, defaultSendTopicList);

        packet.getTraceInfo().setDestinationTopic(destinationTopic);
    }

    @Override
    protected void mapping(ImsPacket<AtMessageReq> packet) {
        Map<String, String> mapping = packet.getTraceInfo().getMapping();

        if (CollectionUtils.isEmpty(mapping)) {
            return ;
        }
        
        // 메시지 내용, 버튼 mapping 치환
    }

    @Override
    protected void send(ImsPacket<AtMessageReq> packet) {
        packet.updateCommand(ImsPacketCommand.SEND_AT);

        kafkaService.sendToKafka(packet.getTraceInfo().getDestinationTopic(), packet);
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
