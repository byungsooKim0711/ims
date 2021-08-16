package org.kimbs.ims.api.gateway.config;

import com.netflix.appinfo.InstanceInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    private final EurekaRegistration registration;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        LocalDateTime startDate = LocalDateTime.now();

        registration.getEurekaClient().setStatus(InstanceInfo.InstanceStatus.DOWN, registration.getApplicationInfoManager().getInfo());

        log.info("[SERVICE DOWN] eureka client. appName: {}, ip: {}, port: {}, elapsedTime: {}",
                registration.getApplicationInfoManager().getInfo().getAppName(),
                registration.getApplicationInfoManager().getInfo().getIPAddr(),
                registration.getApplicationInfoManager().getInfo().getPort(),
                Duration.between(startDate, LocalDateTime.now()));
    }
}
