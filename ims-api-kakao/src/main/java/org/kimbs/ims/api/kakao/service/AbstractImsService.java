package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.kimbs.ims.api.kakao.config.ApiKakaoConfig;
import org.kimbs.ims.exception.ImsDuplicateMsgUidException;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.ImsServiceKeyException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

@Slf4j
public abstract class AbstractImsService<R, M> {

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected ApiKakaoConfig config;

    @Autowired
    protected ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate;

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
    protected abstract void log(M message, RecordMetadata metadata);

    protected void sendToKafka(String topic, M message) throws JsonProcessingException {
        final String data;

        try {
            data = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.error("Json parse error. message: {}", message);
            throw e;
        }

        Mono<SenderResult<Void>> sendResult = reactiveKafkaProducerTemplate.send(topic, data);
        sendResult.doOnNext(result -> log(message, result.recordMetadata()))
                // doOnError에서 onException 호출, R 대신 M 사용
                .doOnError(e -> log.error("exception occurred. topic: {}, data: {}", topic, data, e))
                .subscribe();
    }
}
