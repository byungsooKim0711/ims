package org.kimbs.ims.api.kakao.schedule.cache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class ImsTemplateCodeCache {

    private final Map<String, String> templateCodeMap;

    public ImsTemplateCodeCache(@Qualifier("templateCodeMap") Map<String, String> templateCodeMap) {
        this.templateCodeMap = templateCodeMap;
    }

    @PostConstruct
    public void init() {
    }
}
