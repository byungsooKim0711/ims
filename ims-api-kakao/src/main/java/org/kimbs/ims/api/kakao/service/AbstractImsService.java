package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.config.ApiKakaoConfig;
import org.kimbs.ims.exception.ImsDuplicateMsgUidException;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.ImsServiceKeyException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.AbstractMessage;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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
            // convert message
            M message = convert(request);

            // check validation
            checkServiceKey(serviceKey, message);
            checkSenderKeyAndTemplate(message);
            checkMandatory(message);
            checkLength(message);
            checkDuplicateMsgUid(message);

            // send recv topic
            send(message);

            // logging
            log(message);
        } catch (ImsServiceKeyException e) {
            onException(request, e);
            throw e;
        } catch (ImsMandatoryException e) {
            onException(request, e);
            throw e;
        } catch (ImsTooLongMessageException e) {
            onException(request, e);
            throw e;
        } catch (ImsDuplicateMsgUidException e) {
            onException(request, e);
            throw e;
        } finally {

        }

        // success
        return Mono.just(ImsCommonRes.<Void>builder()
                .code(ResponseCode.SUCCESS)
                .build());
    }

    protected abstract M convert(R request);
    protected abstract void checkServiceKey(String serviceKey, M request) throws ImsServiceKeyException;
    protected abstract void checkSenderKeyAndTemplate(M request);
    protected abstract void checkMandatory(M request) throws ImsMandatoryException;
    protected abstract void checkLength(M request) throws ImsTooLongMessageException;
    protected abstract void checkDuplicateMsgUid(M request);
    protected abstract void send(M message);
    protected abstract void onException(R request, Exception e);
    protected abstract void log(M message);

    protected void sendToKafka(String topic, Object message) throws JsonProcessingException {
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
