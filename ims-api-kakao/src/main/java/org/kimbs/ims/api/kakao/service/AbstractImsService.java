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
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public abstract class AbstractImsService<R, M> {

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected ApiKakaoConfig config;

    public Mono<ImsCommonRes<Void>> sendMessage(String serviceKey, R request) {

        // logic, validation, auth, duplicate_key, etc...
        try {
            // check validation
            String userId = checkServiceKey(serviceKey);
            checkSenderKeyAndTemplate(request);
            checkMandatory(request);
            checkLength(request);
            checkDuplicateMsgUid(request);

            // convert message
            M message = convert(request);

            // add trace info
            addTraceInfo(message, TraceInfo.RECEIVED_AT, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
            addTraceInfo(message, TraceInfo.USER_ID, userId);

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

    protected abstract String checkServiceKey(String serviceKey) throws ImsServiceKeyException;
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
        String data = null;

        try {
            data = mapper.writeValueAsString(message);
            System.out.println(data);
        } catch (JsonProcessingException e) {
            log.error("Json parse error. message: {}", message);
            throw e;
        }
    }
}
