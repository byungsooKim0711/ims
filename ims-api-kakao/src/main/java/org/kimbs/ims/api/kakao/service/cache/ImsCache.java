package org.kimbs.ims.api.kakao.service.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ImsCache {

    @Bean(name = "serviceKeyMap")
    public Map<String, String> serviceKeyMap() {
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "templateCodeMap")
    public Map<String, String> templateCodeMap() {
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "sendProfileMap")
    public Map<String, String> sendProfileMap() {
        return new ConcurrentHashMap<>();
    }
}
