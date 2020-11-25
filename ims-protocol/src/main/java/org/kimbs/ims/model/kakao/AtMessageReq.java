package org.kimbs.ims.model.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AtMessageReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = -2510853128921260272L;

    @JsonProperty("message_type")
    private String messageType;

    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("sender_key")
    private String senderKey;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("app_user_id")
    private String appUserId;

    @JsonProperty("template_code")
    private String templateCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("response_method")
    private String responseMethod;

    @JsonProperty("timeout")
    private int timeout;

    @JsonProperty("attachment")
    private Attachment attachment;

    @JsonProperty("supplement")
    private Supplement supplement;

    @JsonProperty("title")
    private String title;
}
