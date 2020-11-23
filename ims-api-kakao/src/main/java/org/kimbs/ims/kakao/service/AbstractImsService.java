package org.kimbs.ims.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.kimbs.ims.protocol.exception.ServiceKeyException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractImsService<R, M> {

    @Autowired
    private ObjectMapper mapper;

    public ImsCommonRes<Void> sendMessage(String serviceKey, R request) {

        // logic, validation, auth, duplicate_key, etc...

        // success
        return ImsCommonRes.<Void>builder()
                .code(ResponseCode.SUCCESS)
                .build();
    }

    private void checkServiceKey(String serviceKey) throws ServiceKeyException {

        // redis -> select serviceKey -> null ->
        // 나중엔 RequestInterceptor 에서 PathVariable 걸래내자.
        throw new ServiceKeyException(serviceKey);
    }

//    protected abstract void checkLength();
//    protected abstract void checkDuplicateKey();
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
