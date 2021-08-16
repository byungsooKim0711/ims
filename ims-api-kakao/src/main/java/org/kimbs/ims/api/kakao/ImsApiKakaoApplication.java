package org.kimbs.ims.api.kakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEurekaClient
@EnableScheduling
@SpringBootApplication
public class ImsApiKakaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImsApiKakaoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {

	}

}
