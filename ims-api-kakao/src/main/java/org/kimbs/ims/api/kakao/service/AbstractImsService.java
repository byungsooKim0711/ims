package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsServiceKeyException;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractImsService<R, M> {

    @Autowired
    private ObjectMapper mapper;

    public ImsCommonRes<Void> sendMessage(String serviceKey, R request) {

        // logic, validation, auth, duplicate_key, etc...

        checkServiceKey(serviceKey);
        checkMandatory(request);
//        checkDuplicateMsgUid(request);
        checkLength(request);

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

    protected abstract void checkMandatory(R request);
    protected abstract void checkLength(R request);
//    protected abstract void checkDuplicateMsgUid(R request);
//    protected abstract void checkSenderKey();
//    protected abstract void checkTemplate();
//    protected abstract void checkAttachment();
//    protected abstract void checkSupplement();
//    protected abstract void onException();


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
