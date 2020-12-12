package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.config.ApiKakaoConfig;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.ImsServiceKeyException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.AbstractMessage;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.TraceInfo;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
public abstract class AbstractImsService<R, M> {

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected ApiKakaoConfig config;

    @Autowired
    protected KafkaTemplate<String, String> kafkaTemplate;

    public Mono<ImsCommonRes<Void>> sendMessage(String serviceKey, R request) {

        // logic, validation, auth, duplicate_key, etc...
        try {
            // check validation
            checkServiceKey(serviceKey, request);
            checkSenderKeyAndTemplate(request);
            checkMandatory(request);
            checkLength(request);
            checkDuplicateMsgUid(request);

            // get request traceInfo
            Map<TraceInfo, Object> traceInfoMap = ((AbstractMessage) request).getTraceInfo();

            // convert message
            M message = convert(request);

            // add trace info
            addTraceInfo(message, TraceInfo.RECEIVED_AT, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
            // set requestTraceInfo
            ((AbstractMessage) message).addTraceInfo(traceInfoMap);

            // send recv topic
            send(message);
        } catch (ImsServiceKeyException e) {
            onException(request, e);
            throw e;
        } catch (ImsMandatoryException e) {
            onException(request, e);
            throw e;
        } catch (ImsTooLongMessageException e) {
            onException(request, e);
            throw e;
        } finally {
            
        }

        // success
        return Mono.just(ImsCommonRes.<Void>builder()
                .code(ResponseCode.SUCCESS)
                .build());
    }

    protected abstract void checkServiceKey(String serviceKey, R request) throws ImsServiceKeyException;
    protected abstract void checkSenderKeyAndTemplate(R request);
    protected abstract void checkMandatory(R request) throws ImsMandatoryException;
    protected abstract void checkLength(R request) throws ImsTooLongMessageException;
    protected abstract void checkDuplicateMsgUid(R request);
    protected abstract M convert(R request);

    protected void addTraceInfo(M message, TraceInfo info, String trace) {
        ((AbstractMessage) message).addTraceInfo(info, trace);
    }

    protected abstract void send(M message);

    protected abstract void onException(R request, Exception e);

    protected void sendToKafka(String topic, M message) throws JsonProcessingException {
        final String data;

        try {
            data = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.error("Json parse error. message: {}", message);
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
