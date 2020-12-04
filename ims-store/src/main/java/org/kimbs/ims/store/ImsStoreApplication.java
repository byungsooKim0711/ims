package org.kimbs.ims.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ImsStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImsStoreApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {

	}
}
