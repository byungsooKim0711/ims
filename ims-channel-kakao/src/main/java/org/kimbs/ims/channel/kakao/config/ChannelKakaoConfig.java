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

    private String serviceName;
    @NotEmpty
    private String atBaseUrl;
    @NotEmpty
    private String btBaseUrl;
    @NotEmpty
    private String ftBaseUrl;

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