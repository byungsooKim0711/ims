package org.kimbs.ims.api.kakao.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class SendProfileLoadScheduler extends AbstractLoadScheduler {

    @PostConstruct
    public void init() {
    }

    @Scheduled(cron = "${ims.api.kakao.scheduled.redis-load-send-profile-info}")
    @Override
    public void scheduled() {
    }
}
