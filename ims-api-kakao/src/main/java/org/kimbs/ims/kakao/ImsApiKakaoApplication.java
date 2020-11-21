package org.kimbs.ims.kakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ImsApiKakaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImsApiKakaoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {

	}

}
