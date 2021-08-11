package org.kimbs.ims.protocol.v1.mt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsMtReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = 8214461813187362878L;

    // 메시지 유니크 키
    @JsonProperty("message_id")
    private String messageId;

    // 문자 메시지 타입 (SMS, LMS, MMS)
    @JsonProperty("message_type")
    private String messageType;

    // 메시지 제목 (LMS, MMS 사용 가능)
    @JsonProperty("title")
    private String title;

    // 메시지 내용
    @JsonProperty("message")
    private String message;

    // 발신자번호
    @JsonProperty("callback")
    private String callback;

    // 수신자번호
    @JsonProperty("phone_number")
    private String phoneNumber;

    // 첨부파일 리스트 (최대 3개)
    @JsonProperty("attach_file_list")
    private List<String> attachFileList;

    @JsonProperty("template_code")
    private String templateCode;

    @JsonProperty("mapping")
    private Map<String, String> mapping;
}
