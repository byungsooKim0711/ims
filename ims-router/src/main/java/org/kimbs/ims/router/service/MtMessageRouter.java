package org.kimbs.ims.router.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.mt.MtMessageReq;
import org.kimbs.ims.model.mt.MtMessageType;
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
public class MtMessageRouter extends AbstractMessageRouter<MtMessageReq> {

    @Override
    protected void getSendTopic(ImsPacket<MtMessageReq> packet) {
        List<String> defaultSendTopicList = config.getTopics().getSendSm();
        String destinationTopic;
        RoundRobinKey key = RoundRobinKey.SEND_SMS;
        MtMessageReq data = packet.getData();
        MtMessageType messageType = data.getMessageType();

        switch (messageType) {
            case SMS:
                defaultSendTopicList = config.getTopics().getSendSm();
                key = RoundRobinKey.SEND_SMS;
                packet.updateCommand(ImsPacketCommand.SEND_SMS);
                break;
            case LMS:
                defaultSendTopicList = config.getTopics().getSendLm();
                key = RoundRobinKey.SEND_LMS;
                packet.updateCommand(ImsPacketCommand.SEND_LMS);
                break;
            case MMS:
                defaultSendTopicList = config.getTopics().getSendMm();
                key = RoundRobinKey.SEND_MMS;
                packet.updateCommand(ImsPacketCommand.SEND_MMS);
                break;
        }
        destinationTopic = getRoundRobinValue(key, defaultSendTopicList);

        packet.getTraceInfo().setDestinationTopic(destinationTopic);

    }

    @Override
    protected void mapping(ImsPacket<MtMessageReq> packet) {
        Map<String, String> mapping = packet.getTraceInfo().getMapping();

        if (CollectionUtils.isEmpty(mapping)) {
            return ;
        }
        
        // title, message 치환
        String title = packet.getData().getTitle();
        String message = packet.getData().getMessage();
    }

    @Override
    protected void send(ImsPacket<MtMessageReq> packet) {
        kafkaService.sendToKafka(packet.getTraceInfo().getDestinationTopic(), packet);
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
