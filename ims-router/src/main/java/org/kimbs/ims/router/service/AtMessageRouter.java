package org.kimbs.ims.router.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsKafkaSendException;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.util.RoundRobinUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AtMessageRouter extends AbstractMessageRouter<AtMessageReq> {

    @Override
    protected void getSendTopic(AtMessageReq message) {
        List<String> defaultSendTopicList = config.getTopics().getSendAt();
        String destinationTopic = RoundRobinUtils.getRoundRobinValue(RoundRobinUtils.RoundRobinKey.SEND_AT, defaultSendTopicList);

        message.getTrace().setDestinationTopic(destinationTopic);
    }

    @Override
    protected void send(AtMessageReq message) {
        try {
            super.sendToKafka(message.getTrace().getDestinationTopic(), message);
        } catch (JsonProcessingException e) {
            log.error("json parse error. serialNumber: {}, message: {}", message.getSerialNumber(), message, e);
            throw new ImsKafkaSendException(e);
        } catch (Exception e) {
            log.error("exception occurred({}). serialNumber: {}", e.getMessage(), message.getSerialNumber(), e);
            throw new ImsKafkaSendException(e);
        }

    }

    @Override
    protected void log(AtMessageReq message) {

    }

    @Override
    protected ImsAnalyzeLog analyzeLog(AtMessageReq message) {
        ImsAnalyzeLog log = new ImsAnalyzeLog();
        log.setMessage(message.getMessage());
        log.setPhoneNumber(message.getPhoneNumber());
        log.setSerialNumber(message.getSerialNumber());

        return log;
    }
}
