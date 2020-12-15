package org.kimbs.ims.router.service;

import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.util.RoundRobinUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BtMessageRouter extends AbstractMessageRouter<BtMessageReq> {

    @Override
    protected void getSerialNumber(BtMessageReq message) {
        return ;
    }

    @Override
    protected void getSendTopic(BtMessageReq message) {
        List<String> defaultSendTopicList = config.getTopics().getSendBt();
        String destinationTopic = RoundRobinUtils.getRoundRobinValue(RoundRobinUtils.RoundRobinKey.SEND_BT, defaultSendTopicList);

        message.getTrace().setDestinationTopic(destinationTopic);
    }

    @Override
    protected void send(BtMessageReq message) {

    }

    @Override
    protected void log(BtMessageReq message) {

    }

    @Override
    protected ImsAnalyzeLog analyzeLog(BtMessageReq message) {
        ImsAnalyzeLog log = new ImsAnalyzeLog();
        log.setMessage(message.getMessage());
        log.setPhoneNumber(message.getPhoneNumber());
        log.setSerialNumber(message.getSerialNumber());

        return log;
    }
}
