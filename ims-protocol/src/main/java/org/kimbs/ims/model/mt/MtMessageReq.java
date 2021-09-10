package org.kimbs.ims.model.mt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.kimbs.ims.protocol.AbstractMessage;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MtMessageReq extends AbstractMessage {

    private static final long serialVersionUID = 2264937667530612370L;

    @JsonProperty("message_type")
    private MtMessageType messageType;

    @JsonProperty("serial_number")
    private String serialNumber;

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
}
