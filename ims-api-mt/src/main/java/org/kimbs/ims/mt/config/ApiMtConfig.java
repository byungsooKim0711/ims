package org.kimbs.ims.mt.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.style.ToStringCreator;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "ims.api.mt", ignoreUnknownFields = false)
public class ApiMtConfig {

    private String serviceName;
    private Topics topics = new Topics();

    @Data
    public static class Topics {
        @NotEmpty
        private List<String> recvMt;
    }

    @PostConstruct
    public void init() {
        ToStringCreator config = new ToStringCreator(this);
        config.append("service-name", serviceName)
                .append("topics", topics);

        log.info("API-KAKAO-CONFIG: {}", config.toString());
    }
}
