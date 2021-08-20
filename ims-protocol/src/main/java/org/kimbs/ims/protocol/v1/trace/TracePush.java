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
public class TracePush {

    // 앱푸시 요청시간
    @JsonProperty("push_request_at")
    private LocalDateTime pushRequestAt;

    // 앱푸시 응답시간
    @JsonProperty("push_response_at")
    private LocalDateTime pushResponseAt;

    // 앱푸시 응답 PushMessageReq
}
