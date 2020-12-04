package org.kimbs.ims.store.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "ims.store", ignoreUnknownFields = false)
public class StoreConfig {

    private String serviceName;
}
