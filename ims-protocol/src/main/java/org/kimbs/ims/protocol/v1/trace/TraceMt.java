package org.kimbs.ims.protocol.v1.trace;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TraceMt {

    // 문자 요청 시간
    @JsonProperty("mt_request_at")
    private LocalDateTime mtRequestAt;

    // 문자 응답 시간
    @JsonProperty("mt_response_at")
    private LocalDateTime mtResponseAt;

    // 문자 응답 MtMessageRes
}
