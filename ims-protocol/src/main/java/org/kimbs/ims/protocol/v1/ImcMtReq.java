package org.kimbs.ims.protocol.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImcMtReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = 8214461813187362878L;

    // 메시지 유니크 키
    @JsonProperty("msg_uid")
    private String msgUid;

    // 문자 메시지 타입 (SM, LM, MM)
    @JsonProperty("message_type")
    private String messageType;

    // 수신자번호
    @JsonProperty("phone_number")
    private String phoneNumber;

    // 발신자번호
    @JsonProperty("callback")
    private String callback;

    // 메시지 제목
    @JsonProperty("title")
    private String title;

    // 메시지 내용
    @JsonProperty("contents")
    private String contents;

    // 과금코드
    @JsonProperty("bill_code")
    private String billCode;

    // 첨부파일 리스트 (최대 3개)
    @JsonProperty("attach_file_list")
    private List<String> attachFileList;
}
