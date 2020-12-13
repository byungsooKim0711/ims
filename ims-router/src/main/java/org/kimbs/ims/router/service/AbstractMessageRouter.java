package org.kimbs.ims.router.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public abstract class AbstractMessageRouter<M> {

    @Autowired
    private ObjectMapper mapper;

    public void routeAndSend(M message) {


        try {

        } catch (Exception e) {

        } finally {
            ImsAnalyzeLog log = this.commonLog(message);
            log.setDate(LocalDateTime.now());

//            this.sendToKafka();
        }
    }

    protected abstract ImsAnalyzeLog commonLog(M message);

    protected void sendToKafka(String topic, M message) throws JsonProcessingException, Exception {
        final String data;

        try {
            data = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw e;
        }

//        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
//        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//            @Override
//            public void onSuccess(SendResult<String, String> result) {
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                logger.error("kafka send failed. topic: {}, data: {}", topic, data, e.getCause());
//            }
//        });
    }
}
