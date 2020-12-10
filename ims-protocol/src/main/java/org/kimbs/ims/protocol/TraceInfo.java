package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TraceInfo {

    // 유저 아이디
    @JsonProperty("user_id")
    USER_ID(Long.class),

    // 메시지 UID
    @JsonProperty("msg_uid")
    MSG_UID(String.class),

    // 트래킹 ID
    @JsonProperty("tracking_id")
    TRACKING_ID(String.class),

    // 접수시간
    @JsonProperty("received_at")
    RECEIVED_AT(String.class),

    // 라우팅 시간
    @JsonProperty("distribution_at")
    DISTRIBUTION_AT(String.class),

    // 라우팅 토픽
    @JsonProperty("destination_topic")
    DESTINATION_TOPIC(String.class),

    // 카카오 요청/응답
    @JsonProperty("kakao_req_at")
    KAKAO_REQ_AT(String.class),
    @JsonProperty("kakao_res_at")
    KAKAO_RES_AT(String.class),

    // 문자 요청/응답
    @JsonProperty("mt_req_at")
    MT_REQ_AT(String.class),
    @JsonProperty("mt_res_at")
    MT_RES_AT(String.class),

    // 이메일 요청/응답
    @JsonProperty("email_req_at")
    EMAIL_REQ_AT(String.class),
    @JsonProperty("email_res_at")
    EMAIL_RES_AT(String.class),

    // 앱푸시 요청/응답
    @JsonProperty("push_req_at")
    PUSH_REQ_AT(String.class),
    @JsonProperty("push_res_at")
    PUSH_RES_AT(String.class),

    // 사용자 정의 과금 코드
    @JsonProperty("bill_code")
    BILL_CODE(String.class),
    ;

    private final Class<?> type;

    TraceInfo(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }
}
