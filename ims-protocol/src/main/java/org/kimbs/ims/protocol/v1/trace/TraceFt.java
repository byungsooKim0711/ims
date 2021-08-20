package org.kimbs.ims.protocol.v1.trace;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kimbs.ims.model.kakao.FtMessageRes;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TraceFt {

    // 친구톡 요청시간
    @JsonProperty("ft_request_at")
    private LocalDateTime ftRequestAt;

    // 친구톡 응답시간
    @JsonProperty("ft_response_at")
    private LocalDateTime ftResponseAt;

    // 친구톡 응답
    @JsonProperty("ft_message_response")
    private FtMessageRes ftMessageRes;
}
