package org.kimbs.ims.model.redis;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RedisServiceKey implements Serializable {

    private static final long serialVersionUID = 6576977948378776262L;

    private long id;

    private String apiKey;

    private String keyType;

    private boolean useYn;

    private long userId;

    @Builder
    public RedisServiceKey(long id, String apiKey, String keyType, boolean useYn, long userId) {
        this.id = id;
        this.apiKey = apiKey;
        this.keyType = keyType;
        this.useYn = useYn;
        this.userId = userId;
    }
}
