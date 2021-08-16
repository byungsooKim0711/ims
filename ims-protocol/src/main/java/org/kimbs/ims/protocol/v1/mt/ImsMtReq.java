package org.kimbs.ims.protocol.v1.mt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.kimbs.ims.protocol.AbstractMessage;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsMtReq extends AbstractMessage {

    private static final long serialVersionUID = 8214461813187362878L;

    // 메시지 유니크 키
    @NotNull
    @NotBlank(message = "message_id 는 필수값입니다.")
    @Length(min = 1, max = 19, message = "message_id 는 19자 이하여야 합니다.")
    @JsonProperty("message_id")
    private String messageId;

    // 문자 메시지 타입 (SMS, LMS, MMS)
    @NotBlank(message = "message_type 은 필수값입니다.")
    @Length(min = 2, max = 2, message = "message_type 은 2자리여야 합니다.")
    @JsonProperty("message_type")
    private String messageType;

    // 메시지 제목 (LMS, MMS 사용 가능)
    @Length(max = 30, message = "title 은 30자 이하여야 합니다.")
    @JsonProperty("title")
    private String title;

    // 메시지 내용
    @Length(max = 2000, message = "message 는 최대 2000byte 입니다.")
    @JsonProperty("message")
    private String message;

    // 발신자번호
    @NotNull(message = "callback 은 필수값입니다.")
    @Length(max = 20, message = "callback 은 최대 20자 입니다.")
    @JsonProperty("callback")
    private String callback;

    // 수신자번호
    @NotNull(message = "phone_number 은 필수값입니다.")
    @Length(max = 20, message = "phone_number 은 최대 20자 입니다.")
    @JsonProperty("phone_number")
    private String phoneNumber;

    // 첨부파일 리스트 (최대 3개)
    @Valid
    @Size(max = 3, message = "attach_file_list 는 최대 3개까지 사용 가능합니다.")
    @JsonProperty("attach_file_list")
    private List<String> attachFileList;

    @Length(min = 1, max = 30, message = "template_code 값은 30자 이하여야 합니다.")
    @JsonProperty("template_code")
    private String templateCode;

    @JsonProperty("mapping")
    private Map<String, String> mapping;
}
