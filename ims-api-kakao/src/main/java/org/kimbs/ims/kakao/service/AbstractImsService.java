package org.kimbs.ims.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.protocol.exception.ServiceKeyException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractImsService<R, M> {

    @Autowired
    private ObjectMapper mapper;

    public void sendMessage(String serviceKey, R request) {

    }

    private void checkServiceKey(String serviceKey) throws ServiceKeyException {

    }

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
