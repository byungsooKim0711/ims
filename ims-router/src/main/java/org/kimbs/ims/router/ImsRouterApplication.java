package org.kimbs.ims.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ImsRouterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImsRouterApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {

    }

}
