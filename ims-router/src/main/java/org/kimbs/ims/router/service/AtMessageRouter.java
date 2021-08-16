package org.kimbs.ims.router.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.util.RoundRobinUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AtMessageRouter extends AbstractMessageRouter<ImsPacket<AtMessageReq>> {

    @Override
    protected void getSendTopic(ImsPacket<AtMessageReq> message) {
        List<String> defaultSendTopicList = config.getTopics().getSendAt();
        String destinationTopic = RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.SEND_AT, defaultSendTopicList);

        message.getTraceInfo().setDestinationTopic(destinationTopic);
    }

    @Override
    protected void send(ImsPacket<AtMessageReq> message) {
//        try {
//            super.sendToKafka(message.getTrace().getDestinationTopic(), message);
//        } catch (JsonProcessingException e) {
//            log.error("json parse error. serialNumber: {}, message: {}", message.getSerialNumber(), message, e);
//            throw new ImsKafkaSendException(e);
//        } catch (Exception e) {
//            log.error("exception occurred({}). serialNumber: {}", e.getMessage(), message.getSerialNumber(), e);
//            throw new ImsKafkaSendException(e);
//        }

    }

    @Override
    protected void log(ImsPacket<AtMessageReq> message) {

    }

    @Override
    protected ImsAnalyzeLog analyzeLog(ImsPacket<AtMessageReq> message) {
        AtMessageReq data = message.getData();
        ImsAnalyzeLog log = new ImsAnalyzeLog();
        log.setMessage(data.getMessage());
        log.setPhoneNumber(data.getPhoneNumber());
        log.setSerialNumber(data.getSerialNumber());

        return log;
    }
}
