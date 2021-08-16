package org.kimbs.ims.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.event.EventListener;

@EnableEurekaClient
@SpringBootApplication
public class ImsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImsGatewayApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {

	}
}
