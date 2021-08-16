package org.kimbs.ims.router.service;

import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.util.RoundRobinUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtMessageRouter extends AbstractMessageRouter<ImsPacket<FtMessageReq>> {

    @Override
    protected void getSendTopic(ImsPacket<FtMessageReq> message) {
        List<String> defaultSendTopicList = config.getTopics().getSendFt();
        String destinationTopic = RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.SEND_FT, defaultSendTopicList);

        message.getTraceInfo().setDestinationTopic(destinationTopic);
    }

    @Override
    protected void send(ImsPacket<FtMessageReq> message) {

    }

    @Override
    protected void log(ImsPacket<FtMessageReq> message) {

    }

    @Override
    protected ImsAnalyzeLog analyzeLog(ImsPacket<FtMessageReq> message) {
        FtMessageReq data = message.getData();
        ImsAnalyzeLog log = new ImsAnalyzeLog();
        log.setMessage(data.getMessage());
        log.setPhoneNumber(data.getPhoneNumber());
        log.setSerialNumber(data.getSerialNumber());

        return log;
    }
}
