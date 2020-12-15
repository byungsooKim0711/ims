package org.kimbs.ims.model.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AtMessageRes {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;
}
