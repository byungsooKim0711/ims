package org.kimbs.ims.sba;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@EnableAdminServer
@SpringBootApplication
public class ImsAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImsAdminServerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() throws Exception {

	}

}
