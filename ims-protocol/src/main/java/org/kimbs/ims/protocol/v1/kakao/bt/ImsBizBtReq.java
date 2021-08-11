package org.kimbs.ims.protocol.v1.kakao.bt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsBizBtReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = 3021368390494639638L;

    // 접수 유니크 키
    @JsonProperty("msg_uid")
    private String msgUid;

    // 발신 프로필 키
    @JsonProperty("sender_key")
    private String senderKey;

    // 발신 프로필 키
    @JsonProperty("contents")
    private String contents;

    // 국가번호 포함 핸드폰번호
    @JsonProperty("phone_number")
    private String phoneNumber;


}
