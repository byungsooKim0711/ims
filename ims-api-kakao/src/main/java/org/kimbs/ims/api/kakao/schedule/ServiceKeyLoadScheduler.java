package org.kimbs.ims.api.kakao.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.redis.RedisKey;
import org.kimbs.ims.model.redis.RedisServiceKey;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@Component
public class ServiceKeyLoadScheduler extends AbstractLoadScheduler {

    private final Map<String, RedisServiceKey> serviceKeyMap;
    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;
    private final ObjectMapper mapper;

    public ServiceKeyLoadScheduler(@Qualifier("serviceKeyMap") Map<String, RedisServiceKey> serviceKeyMap, ReactiveRedisTemplate<String, String> reactiveRedisTemplate, ObjectMapper mapper) {
        this.serviceKeyMap = serviceKeyMap;
        this.reactiveRedisTemplate = reactiveRedisTemplate;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() {
        scheduled();
    }

    @Scheduled(cron = "${ims.api.kakao.scheduled.redis-load-service-key-info}")
    @Override
    public void scheduled() {
        reactiveRedisTemplate.opsForHash()
                .entries(RedisKey.SERVICE_KEY.name())
                .subscribe(serviceKey -> {
                    try {
                        String key = (String) serviceKey.getKey();
                        RedisServiceKey value = mapper.readValue((String) serviceKey.getValue(), RedisServiceKey.class);

                        serviceKeyMap.put(key, value);
                    } catch (JsonProcessingException e) {
                        log.error("json parse error. key: {}, value: {}", serviceKey.getKey(), serviceKey.getValue());
                    }
                });

    }
}
