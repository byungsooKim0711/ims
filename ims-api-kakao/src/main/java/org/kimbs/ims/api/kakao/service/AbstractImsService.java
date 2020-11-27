package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.ImsServiceKeyException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.AbstractMessage;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.TraceInfo;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public abstract class AbstractImsService<R, M> {

    @Autowired
    private ObjectMapper mapper;

    public ImsCommonRes<Void> sendMessage(String serviceKey, R request) {

        // logic, validation, auth, duplicate_key, etc...

        try {
            checkServiceKey(serviceKey);
//        checkDuplicateMsgUid(request);
            checkMandatory(request);
            checkLength(request);
        } catch (ImsServiceKeyException e) {
            onException(request, e);
            throw e;
        } catch (ImsMandatoryException e) {
            onException(request, e);
            throw e;
        } catch (ImsTooLongMessageException e) {
            onException(request, e);
            throw e;
        } catch (Exception e) {
            onException(request, e);
        } finally {
            
        }

        // success
        return ImsCommonRes.<Void>builder()
                .code(ResponseCode.SUCCESS)
                .build();
    }

    private void checkServiceKey(String serviceKey) throws ImsServiceKeyException {

        // redis -> local map -> select serviceKey -> null ->
        // 나중엔 RequestInterceptor 에서 PathVariable 걸래내자.
        throw new ImsServiceKeyException(serviceKey);
    }

//    protected abstract void checkDuplicateMsgUid(R request);
    protected abstract void checkMandatory(R request) throws ImsMandatoryException;
    protected abstract void checkLength(R request) throws ImsTooLongMessageException;
//    protected abstract void checkSenderKey();
//    protected abstract void checkTemplate();
//    protected abstract void checkAttachment();
//    protected abstract void checkSupplement();
    protected void setTraceInfo(R request) {
        // 접수시간 trace_info 에 추가
        ((AbstractMessage) request).addTraceInfo(TraceInfo.RECEIVED_AT, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    }
    protected abstract void onException(R request, Exception e);


    protected void sendToKafka(String topic, M message) throws JsonProcessingException, Exception {
        String data = null;

        try {
            data = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.error("Json parse error. message: {}", message);
            throw e;
        }
    }
}
