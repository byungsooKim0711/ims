package org.kimbs.ims.router.service;

import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.util.RoundRobinUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtMessageRouter extends AbstractMessageRouter<FtMessageReq> {

    @Override
    protected void getSendTopic(FtMessageReq message) {
        List<String> defaultSendTopicList = config.getTopics().getSendFt();
        String destinationTopic = RoundRobinUtils.getRoundRobinValue(RoundRobinUtils.RoundRobinKey.SEND_FT, defaultSendTopicList);

        message.getTrace().setDestinationTopic(destinationTopic);
    }

    @Override
    protected void send(FtMessageReq message) {

    }

    @Override
    protected void log(FtMessageReq message) {

    }

    @Override
    protected ImsAnalyzeLog analyzeLog(FtMessageReq message) {
        ImsAnalyzeLog log = new ImsAnalyzeLog();
        log.setMessage(message.getMessage());
        log.setPhoneNumber(message.getPhoneNumber());
        log.setSerialNumber(message.getSerialNumber());

        return log;
    }
}
