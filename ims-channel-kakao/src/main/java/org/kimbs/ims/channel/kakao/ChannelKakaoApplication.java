package org.kimbs.ims.channel.kakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ChannelKakaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChannelKakaoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {

    }

}
