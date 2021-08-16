package org.kimbs.ims.api.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ImsApiDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImsApiDiscoveryApplication.class, args);
    }

}
