package org.kimbs.ims.protocol.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kimbs.ims.model.kakao.Attachment;
import org.kimbs.ims.model.kakao.Supplement;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsBizAtReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = 2039270023030263089L;

    // 접수 유니크 키
    @JsonProperty("msg_uid")
    private String msgUid;

    // 발신 프로필 키
    @JsonProperty("sender_key")
    private String senderKey;

    // 템플릿 코드
    @JsonProperty("template_code")
    private String templateCode;

    // 국가번호 포함 수신자번호
    @JsonProperty("phone_number")
    private String phoneNumber;

    // 앱 유저 아이디
    @JsonProperty("app_user_id")
    private String appUserId;

    // 메시지 내용
    @JsonProperty("contents")
    private String contents;

    // 과금코드
    @JsonProperty("bill_code")
    private String billCode;

    // 강조 타이틀 (최대 50자)
    @JsonProperty("title")
    private String title;

    // 버튼 정보
    @JsonProperty("attachment")
    private Attachment attachment;

    // 바로가기 정보
    @JsonProperty("supplement")
    private Supplement supplement;

    // 금액 단위
    @JsonProperty("currency_type")
    private String currencyType;

    // 금액
    @JsonProperty("price")
    private Long price;
}
