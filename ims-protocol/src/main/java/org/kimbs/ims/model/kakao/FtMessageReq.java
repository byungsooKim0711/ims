package org.kimbs.ims.model.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.kimbs.ims.protocol.AbstractMessage;

@Getter
@JsonInclude(Include.NON_EMPTY)
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
	
	@JsonProperty("response_method")
	private ResponseMethod responseMethod;
	
	@JsonProperty("ad_flag")
	private String adFlag = "Y";
	
	@JsonProperty("wide")
	private String wide = "N";
	
	@JsonProperty("attachment")
	private Attachment attachment;

	@Builder
	public FtMessageReq(String serialNumber, KakaoMessageType messageType, String senderKey, String phoneNumber, String appUserId, String userKey, String message, String adFlag, String wide, Attachment attachment) {
		this.serialNumber = serialNumber;
		this.messageType = messageType;
		this.senderKey = senderKey;
		this.phoneNumber = phoneNumber;
		this.appUserId = appUserId;
		this.userKey = userKey;
		this.message = message;
		this.adFlag = adFlag;
		this.wide = wide;
		this.attachment = attachment;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
