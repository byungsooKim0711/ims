package org.kimbs.ims.router.service;

import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.util.RoundRobinUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BtMessageRouter extends AbstractMessageRouter<ImsPacket<BtMessageReq>> {

    @Override
    protected void getSendTopic(ImsPacket<BtMessageReq> message) {
        List<String> defaultSendTopicList = config.getTopics().getSendBt();
        String destinationTopic = RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.SEND_BT, defaultSendTopicList);

        message.getTraceInfo().setDestinationTopic(destinationTopic);
    }

    @Override
    protected void send(ImsPacket<BtMessageReq> message) {

    }

    @Override
    protected void log(ImsPacket<BtMessageReq> message) {

    }

    @Override
    protected ImsAnalyzeLog analyzeLog(ImsPacket<BtMessageReq> message) {
        BtMessageReq data = message.getData();
        ImsAnalyzeLog log = new ImsAnalyzeLog();
        log.setMessage(data.getMessage());
        log.setPhoneNumber(data.getPhoneNumber());
        log.setSerialNumber(data.getSerialNumber());

        return log;
    }
}
