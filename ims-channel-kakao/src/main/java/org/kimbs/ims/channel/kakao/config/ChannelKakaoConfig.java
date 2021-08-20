package org.kimbs.ims.channel.kakao.config;

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
@ConfigurationProperties(prefix = "ims.channel.kakao", ignoreUnknownFields = false)
public class ChannelKakaoConfig {

    private boolean simulation = true;
    private boolean wiretap = true;

    private String serviceName;

    @NotEmpty
    private String baseUrl;
    @NotEmpty
    private String atPath;
    @NotEmpty
    private String btPath;
    @NotEmpty
    private String ftPath;

    private int connectTimeout = 3000;
    private long responseTimeout = 3000;
    private long readTimeout = 3000;
    private long writeTimeout = 3000;

    private Topics topics = new Topics();

    @Data
    public static class Topics {
        @NotEmpty
        private List<String> sendAt;
        @NotEmpty
        private List<String> sendFt;
        @NotEmpty
        private List<String> sendBt;
    }

    @PostConstruct
    public void init() {
        ToStringCreator config = new ToStringCreator(this);
        config.append("service-name", serviceName)
                .append("topics", topics);

        log.info("IMS-CHANNEL-KAKAO: {}", config.toString());
    }
}