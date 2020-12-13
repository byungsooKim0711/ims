package org.kimbs.ims.model.redis;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RedisTemplateCode implements Serializable {

    private static final long serialVersionUID = 957597918063145154L;
}
