package org.kimbs.ims.model.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.kimbs.ims.protocol.AbstractMessage;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FtMessageReq extends AbstractMessage {

	private static final long serialVersionUID = -1142822831531186105L;

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
	
	@JsonProperty("user_key")
	private String userKey;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("ad_flag")
	private String adFlag = "Y";
	
	@JsonProperty("attachment")
	private Attachment attachment;

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
