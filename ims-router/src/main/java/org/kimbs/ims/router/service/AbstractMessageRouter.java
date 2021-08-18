package org.kimbs.ims.router.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.protocol.ImsAnalyzeLog;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.router.config.RouterConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * P: Packet
 */
@Slf4j
public abstract class AbstractMessageRouter<P> {

    @Autowired
    protected RouterConfig config;

    @Autowired
    private ObjectMapper mapper;

    public void routeAndSend(P message) throws Exception {

        try {
            ((ImsPacket<?>) message).getTraceInfo().setDistributionAt(LocalDateTime.now());

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
            log.setUserId(((ImsPacket<?>) message).getTraceInfo().getCustomerId());

            System.out.println(log);
//            this.sendToKafka(RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.ANALYZE_LOG, config.getTopics().getAnalyzeLog()), log);
        }
    }

    protected abstract void getSendTopic(P messagePacket);
    protected abstract void send(P messagePacket);
    protected abstract void log(P messagePacket);
    protected abstract ImsAnalyzeLog analyzeLog(P messagePacket);
}
