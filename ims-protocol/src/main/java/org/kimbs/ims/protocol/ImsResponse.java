package org.kimbs.ims.protocol;

import lombok.Builder;
import lombok.Getter;
import org.kimbs.ims.protocol.code.ResponseCode;

import java.time.LocalDateTime;

@Getter
public class ImsResponse<T> {

    // 응답 코드
    private final String code;

    // 응답 메시지
    private final String message;

    // 응답 데이터
    private final T data;

    // 응답 시간
    private final LocalDateTime time;

    @Builder
    public ImsResponse(ResponseCode code, T data) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = data;
        this.time = LocalDateTime.now();
    }

}
