package org.kimbs.ims.api.kakao.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TemplateLoadScheduler extends AbstractLoadScheduler {

    @PostConstruct
    public void init() {

    }

    @Scheduled(cron = "${ims.api.kakao.scheduled.redis-load-template-code-info}")
    @Override
    public void scheduled() {

    }
}
