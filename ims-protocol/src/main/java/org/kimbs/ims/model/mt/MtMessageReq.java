package org.kimbs.ims.model.mt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.kimbs.ims.model.kakao.KakaoMessageType;
import org.kimbs.ims.protocol.AbstractMessage;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MtMessageReq extends AbstractMessage {

    private static final long serialVersionUID = 2264937667530612370L;

    @JsonProperty("message_type")
    private String messageType;

    @JsonProperty("serial_number")
    private String serialNumber;
}
