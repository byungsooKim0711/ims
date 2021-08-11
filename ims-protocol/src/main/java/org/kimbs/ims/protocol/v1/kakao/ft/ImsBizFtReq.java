package org.kimbs.ims.protocol.v1.kakao.ft;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.kimbs.ims.model.kakao.Attachment;
import org.kimbs.ims.protocol.AbstractMessage;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImsBizFtReq extends AbstractMessage {

    private static final long serialVersionUID = -8228424510872410358L;

    // 메시지 유니크 키
    @NotNull
    @NotBlank(message = "message_id 는 필수값입니다.")
    @Length(min = 1, max = 30, message = "message_id 는 30자 이하여야 합니다.")
    @JsonProperty("message_id")
    private String messageId;

    // 친구톡 메시지 타입
    @NotBlank(message = "message_type 은 필수값입니다.")
    @Length(min = 2, max = 2, message = "message_type 은 2자리여야 합니다.")
    @JsonProperty("message_type")
    private String messageType;

    // 발신 프로필 키
    @NotBlank(message = "sender_key 는 필수값입니다.")
    @Length(min = 1, max = 40, message = "sender_key 값은 40자 이하여야 합니다.")
    @JsonProperty("sender_key")
    private String senderKey;

    // 앱 유저 아이디
    @Length(min = 1, max = 20, message = "app_user_id 값은 20자 이하여야 합니다.")
    @JsonProperty("app_user_id")
    private String appUserId;

    // 사용자 식별키, 카카오톡 채널 봇을 이용해 받은 카카오톡 채널 사용자 식별키
    @JsonProperty("user_key")
    private String userKey;

    // 국가코드
    @Length(max = 10, message = "country_code 값은 10자 이하여야 합니다.")
    @JsonProperty("country_code")
    private String countryCode;

    // 수신자번호
    @Length(min = 1, max = 20, message = "phone_number 값은 20자 이하여야 합니다.")
    @JsonProperty("phone_number")
    private String phoneNumber;

    // 알림톡 메시지 내용
    @JsonProperty("message")
    private String message;

    // (광고) 문구와 수신거부 문구 표시여부
    @Length(min = 1, max = 1)
    @JsonProperty("ad_flag")
    private String adFlag = "Y";

    // 버튼 정보
    @JsonProperty("attachment")
    private Attachment attachment;

    @JsonProperty("mapping")
    private Map<String, String> mapping;
}
