package org.kimbs.ims.model.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;
import java.util.Map;

/**
 * 브랜드톡 일반형
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BtMessageReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = -5943369529462819244L;

    @JsonProperty("request_timeout")
    private int requestTimeout;

    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("sender_key")
    private String senderKey;

    @JsonProperty("message_type")
    private String messageType;

    @JsonProperty("template_code")
    private String templateCode;

    @JsonProperty("content_type")
    private String contentType;

    @JsonProperty("message")
    private String message;

    @JsonProperty("attachment")
    private Attachment attachment;

    @JsonProperty("message_variable")
    private Map<String, String> messageVariable;

    @JsonProperty("button_variable")
    private Map<String, String> buttonVariable;

    @JsonProperty("response_method")
    private ResponseMethod responseMethod;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("targeting")
    private String targeting;
}
