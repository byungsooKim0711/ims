package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.kimbs.ims.protocol.code.ResponseCode;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsCommonRes<T> {

    // 응답 코드
    @JsonProperty("code")
    private final String code;

    // 응답 메시지
    @JsonProperty("message")
    private final String message;

    // 응답 데이터
    @JsonProperty("data")
    private final T data;

    // 응답 시간
    @JsonProperty("time")
    private final LocalDateTime time;

    @Builder
    public ImsCommonRes(ResponseCode code, T data) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = data;
        this.time = LocalDateTime.now();
    }

}
