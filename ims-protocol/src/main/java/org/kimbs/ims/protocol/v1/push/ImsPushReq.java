package org.kimbs.ims.protocol.v1.push;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;

@Getter
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsPushReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = -3057506793548665520L;

    // 메시지 유니크 아이디
    @JsonProperty("msg_uid")
    private String msgUid;

    // 메시지 형식
    @JsonProperty("push_type")
    private String pushType;

    // 메시지 제목
    @JsonProperty("title")
    private String title;

    // 메시지 내용
    @JsonProperty("message")
    private String message;

    // 과금 코드
    @JsonProperty("bill_code")
    private String billCode;
}
