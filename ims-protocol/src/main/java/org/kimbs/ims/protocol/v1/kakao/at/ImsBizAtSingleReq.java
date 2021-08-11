package org.kimbs.ims.protocol.v1.kakao.at;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.kimbs.ims.model.kakao.Attachment;
import org.kimbs.ims.model.kakao.Supplement;
import org.kimbs.ims.protocol.AbstractMessage;
import org.kimbs.ims.protocol.TraceInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImsBizAtSingleReq extends AbstractMessage {

    private static final long serialVersionUID = -610161648011394064L;

    // 메시지 유니크 키
//    @NotNull
//    @NotBlank
//    @Length(min = 1, max = 30)
    @JsonProperty("message_id")
    private String messageId;

    // 알림톡 메시지 타입
//    @NotBlank
//    @Length(min = 2, max = 2)
    @JsonProperty("message_type")
    private String messageType;

    // 발신 프로필 키
//    @Valid
//    @NotBlank
//    @Length(min = 1, max = 40)
    @JsonProperty("sender_key")
    private String senderKey;

    // 템플릿 코드
//    @Valid
//    @NotBlank
//    @Length(min = 1, max = 30)
    @JsonProperty("template_code")
    private String templateCode;

    // 메시지 내용
//    @Length(min = 1, max = 1000)
    @JsonProperty("message")
    private String message;

    // 강조 타이틀 (최대 50자)
//    @Length(max = 50)
    @JsonProperty("title")
    private String title;

//    @Length(max = 16)
    @JsonProperty("header")
    private String header;

    // 버튼 정보
    @JsonProperty("attachment")
    private Attachment attachment;

    // 바로가기 정보
    @JsonProperty("supplement")
    private Supplement supplement;

    // 국가코드
//    @Length(max = 10)
    @JsonProperty("phone_number")
    private String countryCode;

    // 수신자번호
//    @Length(min = 1, max = 20)
    @JsonProperty("phone_number")
    private String phoneNumber;

    // 앱 유저 아이디
//    @Length(min = 1, max = 20)
    @JsonProperty("app_user_id")
    private String appUserId;

    @JsonProperty("mapping")
    private Map<String, String> mapping;

}
