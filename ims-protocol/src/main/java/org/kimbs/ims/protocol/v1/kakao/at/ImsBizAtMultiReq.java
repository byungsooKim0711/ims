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
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsBizAtMultiReq extends AbstractMessage {

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
    @Size(min = 1, max = 1000)
    @JsonProperty("receiver_list")
    private List<Receiver> receiverList;

    // 메시지 내용
    @Length(min = 1, max = 1000)
    @JsonProperty("message")
    private String message;

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
