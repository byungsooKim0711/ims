package org.kimbs.ims.api.kakao.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@Component
public class ServiceKeyLoadScheduler extends AbstractLoadScheduler {

    private final Map<String, String> serviceKeyMap;

    public ServiceKeyLoadScheduler(@Qualifier("serviceKeyMap") Map<String, String> serviceKeyMap) {
        this.serviceKeyMap = serviceKeyMap;
    }

    @PostConstruct
    public void init() {
        scheduled();
    }

    @Scheduled(cron = "${ims.api.kakao.scheduled.redis-load-service-key-info}")
    @Override
    public void scheduled() {

    }
}
