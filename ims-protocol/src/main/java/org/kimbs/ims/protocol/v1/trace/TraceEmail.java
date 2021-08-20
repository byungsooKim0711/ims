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
public class TraceEmail {

    // 이메일 요청시간
    @JsonProperty("email_request_at")
    private LocalDateTime emailRequestAt;

    // 이메일 응답시간
    @JsonProperty("email_response_at")
    private LocalDateTime emailResponseAt;

    // 이메일 응닫 EmailMessageRes
}
