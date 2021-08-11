package org.kimbs.ims.protocol.v1.kakao.at;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.kimbs.ims.model.kakao.Attachment;
import org.kimbs.ims.model.kakao.Supplement;
import org.kimbs.ims.protocol.AbstractMessage;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImsBizAtReq extends AbstractMessage {

    private static final long serialVersionUID = 4701000240446112250L;

    // 메시지 유니크 키
    @NotNull
    @NotBlank(message = "message_id 는 필수값입니다.")
    @Length(min = 1, max = 30, message = "message_id 는 30자 이하여야 합니다.")
    @JsonProperty("message_id")
    private String messageId;

    // 알림톡 메시지 타입
    @NotBlank(message = "message_type 은 필수값입니다.")
    @Length(min = 2, max = 2, message = "message_type 은 2자리여야 합니다.")
    @JsonProperty("message_type")
    private String messageType;

    // 발신 프로필 키
    @NotBlank(message = "sender_key 는 필수값입니다.")
    @Length(min = 1, max = 40, message = "sender_key 값은 40자 이하여야 합니다.")
    @JsonProperty("sender_key")
    private String senderKey;

    // 템플릿 코드
    @NotBlank(message = "template_code 값은 필수값입니다.")
    @Length(min = 1, max = 30, message = "template_code 값은 30자 이하여야 합니다.")
    @JsonProperty("template_code")
    private String templateCode;

    // 메시지 내용
    @Length(min = 1, max = 1000, message = "message 값은 1000자 이하여야 합니다.")
    @JsonProperty("message")
    private String message;

    // 강조 타이틀 (최대 50자)
    @Length(max = 50, message = "title 값은 50자 이하여야 합니다.")
    @JsonProperty("title")
    private String title;

    @Length(max = 16, message = "header 값은 16자 이하여야 합니다.")
    @JsonProperty("header")
    private String header;

    // 버튼 정보
    @JsonProperty("attachment")
    private Attachment attachment;

    // 바로가기 정보
    @JsonProperty("supplement")
    private Supplement supplement;

    // 국가코드
    @Length(max = 10, message = "country_code 값은 10자 이하여야 합니다.")
    @JsonProperty("country_code")
    private String countryCode;

    // 수신자번호
    @Length(min = 1, max = 20, message = "phone_number 값은 20자 이하여야 합니다.")
    @JsonProperty("phone_number")
    private String phoneNumber;

    // 앱 유저 아이디
    @Length(min = 1, max = 20, message = "app_user_id 값은 20자 이하여야 합니다.")
    @JsonProperty("app_user_id")
    private String appUserId;

    @JsonProperty("mapping")
    private Map<String, String> mapping;
}
