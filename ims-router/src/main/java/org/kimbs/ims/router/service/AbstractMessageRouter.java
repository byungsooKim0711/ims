package org.kimbs.ims.router.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.config.RouterConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Slf4j
public abstract class AbstractMessageRouter<T> {

    @Autowired
    protected RouterConfig config;

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected KafkaService kafkaService;

    public void routeAndSend(ImsPacket<T> packet) {

        try {
            packet.getTraceInfo().setDistributionAt(LocalDateTime.now());

            // default routing
            // pattern routing
            // setting routing
            // 우선 default 값만 사용
            getSendTopic(packet);

            // 전환발송 등 설정

            // 발송
            send(packet);

            // 로깅
            log(packet);
        } catch (Exception e) {

        } finally {
            ImsAnalyzeLog log = this.analyzeLog(packet);
            log.setDate(LocalDateTime.now());
            log.setUserId(packet.getTraceInfo().getCustomerId());

            System.out.println(log);
//            this.sendToKafka(RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.ANALYZE_LOG, config.getTopics().getAnalyzeLog()), log);
        }
    }

    protected abstract void getSendTopic(ImsPacket<T> packet);
    protected abstract void send(ImsPacket<T> packet);
    protected abstract void log(ImsPacket<T> packet);
    protected abstract ImsAnalyzeLog analyzeLog(ImsPacket<T> packet);
}
