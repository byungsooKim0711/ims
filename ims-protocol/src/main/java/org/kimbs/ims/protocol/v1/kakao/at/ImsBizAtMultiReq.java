package org.kimbs.ims.protocol.v1.kakao.at;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.kimbs.ims.model.kakao.Attachment;
import org.kimbs.ims.model.kakao.Supplement;
import org.kimbs.ims.protocol.AbstractMessage;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsBizAtMultiReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = 1358042691986250479L;

    // 메시지 유니크 키
    @NotNull
    @NotBlank
    @Length(min = 1, max = 30)
    @JsonProperty("msg_uid")
    private String msgUid;

    // 발신 프로필 키
    @Valid
    @NotBlank
    @Length(min = 1, max = 40)
    @JsonProperty("sender_key")
    private String senderKey;

    // 템플릿 코드
    @Valid
    @NotBlank
    @Length(min = 1, max = 30)
    @JsonProperty("template_code")
    private String templateCode;

    // 수신자 리스트 최대 1000 건
    @Valid
    @NotEmpty
    @Length(min = 1, max = 1000)
    @JsonProperty("template_code")
    private List<Receiver> receiverList;

    // 국가번호 포함 수신자번호
    @Length(min = 1, max = 20)
    @JsonProperty("phone_number")
    private String phoneNumber;

    // 앱 유저 아이디
    @Length(min = 1, max = 20)
    @JsonProperty("app_user_id")
    private String appUserId;

    // 메시지 내용
    @Length(min = 1, max = 1000)
    @JsonProperty("contents")
    private String contents;

    // 과금코드
    @Length(min = 1, max = 20)
    @JsonProperty("bill_code")
    private String billCode;

    // 강조 타이틀 (최대 50자)
    @Length(min = 1, max = 50)
    @JsonProperty("title")
    private String title;

    // 버튼 정보
    @JsonProperty("attachment")
    private Attachment attachment;

    // 바로가기 정보
    @JsonProperty("supplement")
    private Supplement supplement;
}
