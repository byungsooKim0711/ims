package org.kimbs.ims.protocol.v1.trace;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kimbs.ims.model.kakao.AtMessageRes;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TraceAt {

    // 알림톡 요청시간
    @JsonProperty("at_request_at")
    private LocalDateTime atRequestAt;

    // 알림톡 응답시간
    @JsonProperty("at_response_at")
    private LocalDateTime atResponseAt;

    // 알림톡 응답
    @JsonProperty("at_message_response")
    private AtMessageRes atMessageRes;
}
