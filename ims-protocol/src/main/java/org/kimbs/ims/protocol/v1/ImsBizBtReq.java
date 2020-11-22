package org.kimbs.ims.protocol.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsBizBtReq implements Serializable {

    private static final long serialVersionUID = 3021368390494639638L;

    // 접수 유니크 키
    @JsonProperty("msg_uid")
    private String msg_uid;

    // 발신 프로필 키
    @JsonProperty("sender_key")
    private String senderKey;

}
