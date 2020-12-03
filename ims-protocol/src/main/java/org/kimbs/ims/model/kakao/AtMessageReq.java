package org.kimbs.ims.model.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;

@Getter
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AtMessageReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = -2510853128921260272L;

    @JsonProperty("message_type")
    private KakaoMessageType messageType;

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
    private ResponseMethod responseMethod;

    @JsonProperty("timeout")
    private int timeout;

    @JsonProperty("attachment")
    private Attachment attachment;

    @JsonProperty("supplement")
    private Supplement supplement;

    @JsonProperty("title")
    private String title;

    @Builder
    public AtMessageReq(KakaoMessageType messageType, String senderKey, String phoneNumber, String appUserId, String templateCode, String message, String title, Attachment attachment, Supplement supplement) {
        this.messageType = messageType;
        this.senderKey = senderKey;
        this.phoneNumber = phoneNumber;
        this.appUserId = appUserId;
        this.templateCode = templateCode;
        this.message = message;
        this.title = title;
        this.attachment = attachment;
        this.supplement = supplement;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
