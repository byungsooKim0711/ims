package org.kimbs.ims.store.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.model.redis.RedisServiceKey;
import org.kimbs.ims.model.redis.RedisStoreKey;
import org.kimbs.ims.store.domain.ServiceKey;
import org.kimbs.ims.store.repository.ServiceKeyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Component
public class RedisStoreServiceKeySchedule {

    private final ServiceKeyRepository serviceKeyRepository;
    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final ObjectMapper mapper;

    private static final String KEY = RedisStoreKey.SERVICE_KEY.name();

    @PostConstruct
    public void init() {
        schedule();
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void schedule() {
        LocalDateTime startTime = LocalDateTime.now();

        Page<ServiceKey> serviceKeyPage = null;
        List<ServiceKey> serviceKeyList = null;

        int pageSize = 500;
        int page = 0;
        long total = 0;

        while (true) {
            serviceKeyPage = serviceKeyRepository.findAll(PageRequest.of(page, pageSize));
            serviceKeyList = serviceKeyPage.getContent();

            if (CollectionUtils.isEmpty(serviceKeyList)) {
                break;
            }

            total += serviceKeyList.size();

            for (ServiceKey serviceKey : serviceKeyList) {
                RedisServiceKey redisServiceKey = RedisServiceKey.builder()
                        .id(serviceKey.getId())
                        .apiKey(serviceKey.getApiKey())
                        .keyType(serviceKey.getKeyType().name())
                        .useYn(serviceKey.isUseYn())
                        .userId(serviceKey.getWebUser().getId())
                        .build();

                try {
                    redisTemplate.opsForHash().put(KEY, redisServiceKey.getApiKey(), mapper.writeValueAsString(redisServiceKey));
                } catch (JsonProcessingException e) {
                    log.error("json parse error. apiKey: {}, type: {}", redisServiceKey.getApiKey(), redisServiceKey.getKeyType());
                }
            }
            page++;
        }

        log.info("[Redis-Store] serviceKey. total: {}, elapsed time: {}", total, Duration.between(startTime, LocalDateTime.now()));
    }
}
