package org.kimbs.ims.kakao.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ims.api.kakao", ignoreUnknownFields = false)
public class ApiKakaoConfig {

    private String serviceName;
}
