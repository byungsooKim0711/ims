package org.kimbs.ims.api.kakao.service.cache;

import org.kimbs.ims.exception.ImsServiceKeyException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class ImsServiceKeyCache {

    private final Map<String, String> serviceKeyMap;

    public ImsServiceKeyCache(@Qualifier("serviceKeyMap") Map<String, String> serviceKeyMap) {
        this.serviceKeyMap = serviceKeyMap;
    }

    @PostConstruct
    public void init() {
    }

    public String findServiceKey(String serviceKey) {
        String key = serviceKeyMap.get(serviceKey);

        if (key == null) {
            throw new ImsServiceKeyException(serviceKey);
        }

        return key;
    }
}
