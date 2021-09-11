package org.kimbs.ims.api.kakao.schedule.cache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class ImsSendProfileCache {

    private final Map<String, String> sendProfileMap;

    public ImsSendProfileCache(@Qualifier("sendProfileMap") Map<String, String> sendProfileMap) {
        this.sendProfileMap = sendProfileMap;
    }

    @PostConstruct
    public void init() {
    }
}
