package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kimbs.ims.protocol.code.ResponseCode;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsApiResult<T> {

    // 응답 코드
    @JsonProperty("code")
    private final String code;

    // 응답 메시지
    @JsonProperty("message")
    private final String message;

    // 응답 데이터
    @JsonProperty("data")
    private final T data;

    @Builder
    private ImsApiResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /** success */
    public static <T> ImsApiResult<T> succeed(String code, String message, T data) {
        return ImsApiResult.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ImsApiResult<T> succeed(ResponseCode responseCode) {
        return succeed(responseCode.getCode(), null, null);
    }

    public static <T> ImsApiResult<T> succeed(String code) {
        return succeed(code, null, null);
    }

    public static <T> ImsApiResult<T> succeed(String code, T data) {
        return succeed(code, null, data);
    }

    /** failed */
    public static <T> ImsApiResult<T> failed(String code, String message) {
        return ImsApiResult.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    public static <T> ImsApiResult<T> failed(ResponseCode code) {
        return ImsApiResult.<T>builder()
                .code(code.getCode())
                .message(code.getMessage())
                .build();
    }

    public static <T> ImsApiResult<T> failed(ResponseCode code, String message) {
        return ImsApiResult.<T>builder()
                .code(code.getCode())
                .message(message)
                .build();
    }
}
