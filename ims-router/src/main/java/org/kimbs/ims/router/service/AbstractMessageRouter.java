package org.kimbs.ims.router.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.protocol.AbstractMessage;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.router.config.RouterConfig;
import org.kimbs.ims.util.RoundRobinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;

@Slf4j
public abstract class AbstractMessageRouter<M> {

    @Autowired
    protected RouterConfig config;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void routeAndSend(M message) throws Exception {

        try {
            ((AbstractMessage) message).getTrace().setDistributionAt(LocalDateTime.now());

            // default routing
            // pattern routing
            // setting routing
            // 우선 default 값만 사용
            getSendTopic(message);

            // 전환발송 등 설정

            // 로깅
            log(message);

            // 발송
            send(message);
        } catch (Exception e) {

        } finally {
            ImsAnalyzeLog log = this.analyzeLog(message);
            log.setDate(LocalDateTime.now());
            log.setUserId(((AbstractMessage) message).getTrace().getUserId());

            System.out.println(log);
            this.sendToKafka(RoundRobinUtils.getRoundRobinValue(RoundRobinUtils.RoundRobinKey.ANALYZE_LOG, config.getTopics().getAnalyzeLog()), log);
        }
    }

    protected abstract void getSendTopic(M message);
    protected abstract void send(M message);
    protected abstract void log(M message);
    protected abstract ImsAnalyzeLog analyzeLog(M message);
    
    protected void sendToKafka(String topic, Object message) throws JsonProcessingException, Exception {
        final String data;

        try {
            data = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw e;
        }

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
            }

            @Override
            public void onFailure(Throwable e) {
                log.error("kafka send failed. topic: {}, data: {}", topic, data, e.getCause());
            }
        });
    }
}
