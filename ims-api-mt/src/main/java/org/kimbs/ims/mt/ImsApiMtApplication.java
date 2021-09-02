package org.kimbs.ims.mt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ImsApiMtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImsApiMtApplication.class, args);
    }

}
