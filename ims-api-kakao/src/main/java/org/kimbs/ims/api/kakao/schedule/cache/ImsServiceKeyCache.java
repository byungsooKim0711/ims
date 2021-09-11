package org.kimbs.ims.api.kakao.schedule.cache;

import org.kimbs.ims.exception.ImsServiceKeyException;
import org.kimbs.ims.model.redis.RedisServiceKey;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class ImsServiceKeyCache {

    private final Map<String, RedisServiceKey> serviceKeyMap;

    public ImsServiceKeyCache(@Qualifier("serviceKeyMap") Map<String, RedisServiceKey> serviceKeyMap) {
        this.serviceKeyMap = serviceKeyMap;
    }

    @PostConstruct
    public void init() {
    }

    public Long findServiceKey(String serviceKey) {
        RedisServiceKey key = serviceKeyMap.get(serviceKey);

        if (key == null) {
            throw new ImsServiceKeyException(serviceKey);
        }

        return key.getUserId();
    }
}
