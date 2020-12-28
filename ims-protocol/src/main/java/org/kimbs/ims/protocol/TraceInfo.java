package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TraceInfo implements Serializable {

    private static final long serialVersionUID = -2313086246211220293L;

    // 유저 아이디
    @JsonProperty("user_id")
    private Long userId;

    // 메시지 UID
    @JsonProperty("msg_uid")
    private String msgUid;

    // 트래킹 ID
    @JsonProperty("tracking_id")
    private String trackingId;

    // 접수 시간
    @JsonProperty("received_at")
    private LocalDateTime receivedAt;

    // 라우팅 시간
    @JsonProperty("distribution_at")
    private LocalDateTime distributionAt;

    // 라우팅 토픽
    @JsonProperty("destination_topic")
    private String destinationTopic;

    // 카카오 요청/응답
    @JsonProperty("kakao_req_at")
    private LocalDateTime kakaReqAt;
    @JsonProperty("kakao_res_at")
    private LocalDateTime kakaResAt;

    // 문자 요청/응답
    @JsonProperty("mt_req_at")
    private LocalDateTime mtReqAt;
    @JsonProperty("mt_res_at")
    private LocalDateTime mtResAt;

    // 이메일 요청/응답
    @JsonProperty("email_req_at")
    private LocalDateTime emailReqAt;
    @JsonProperty("email_res_at")
    private LocalDateTime emailResAt;

    // 앱푸시 요청/응답
    @JsonProperty("push_req_at")
    private LocalDateTime pushReqAt;
    @JsonProperty("push_res_at")
    private LocalDateTime pushResAt;

    // 부서코드
    @JsonProperty("bill_code")
    private String billCode;
}
