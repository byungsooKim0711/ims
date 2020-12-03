package org.kimbs.ims.protocol.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kimbs.ims.model.kakao.Attachment;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsBizFtReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = -8228424510872410358L;

    // 접수 유니크 키
    @JsonProperty("msg_uid")
    private String msgUid;

    // 발신 프로필 키
    @JsonProperty("sender_key")
    private String senderKey;

    // 앱 유저 아이디
    @JsonProperty("app_user_id")
    private String appUserId;

    // 사용자 식별키, 카카오톡 채널 봇을 이용해 받은 카카오톡 채널 사용자 식별키
    @JsonProperty("user_key")
    private String userKey;

    // 국가번호 포함 핸드폰번호
    @JsonProperty("phone_number")
    private String phoneNumber;

    // T(TEXT), I(IMAGE), W(WIDE IMAGE)
    @JsonProperty("ft_type")
    private String ftType;

    // 알림톡 메시지 내용
    @JsonProperty("contents")
    private String contents;

    // (광고) 문구와 수신거부 문구 표시여부
    @JsonProperty("ad_flag")
    private String adFlag = "Y";

    // 과금 코드
    @JsonProperty("bill_code")
    private String billCode;

    // 버튼 정보
    @JsonProperty("attachment")
    private Attachment attachment;
}
