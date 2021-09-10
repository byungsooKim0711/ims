package org.kimbs.ims.router.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.util.RoundRobinUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BtMessageRouter extends AbstractMessageRouter<BtMessageReq> {

    @Override
    protected void getSendTopic(ImsPacket<BtMessageReq> packet) {
        List<String> defaultSendTopicList = config.getTopics().getSendBt();
        String destinationTopic = RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.SEND_BT, defaultSendTopicList);

        packet.getTraceInfo().setDestinationTopic(destinationTopic);
    }

    @Override
    protected void mapping(ImsPacket<BtMessageReq> packet) {
        Map<String, String> mapping = packet.getTraceInfo().getMapping();

        if (CollectionUtils.isEmpty(mapping)) {
            return ;
        }

        // 메시지 내용, 버튼 mapping 치환
    }

    @Override
    protected void send(ImsPacket<BtMessageReq> packet) {

    }

    @Override
    protected void log(ImsPacket<BtMessageReq> packet) {
        log.info("[PU-LOG] command: {}, trackingId: {}, serialNumber: {}", packet.getCommand(), packet.getTraceInfo().getTrackingId(), packet.getData().getSerialNumber());
    }

    @Override
    protected ImsAnalyzeLog analyzeLog(ImsPacket<BtMessageReq> packet) {
        BtMessageReq data = packet.getData();
        ImsAnalyzeLog log = new ImsAnalyzeLog();
        log.setMessage(data.getMessage());
        log.setPhoneNumber(data.getPhoneNumber());
        log.setSerialNumber(data.getSerialNumber());

        return log;
    }
}
