package org.kimbs.ims.protocol;

public enum TraceInfo {

    // 유저 아이디
    USER_ID,

    // 메시지 UID
    MSG_UID,

    // 접수시간
    RECEIVED_AT,

    // 라우팅 시간
    DISTRIBUTION_AT,

    // 라우팅 토픽
    DESTINATION_TOPIC,

    // 카카오 요청/응답
    KAKAO_REQ_AT,
    KAKAO_RES_AT,

    // 문자 요청/응답
    MT_REQ_AT,
    MT_RES_AT,

    // 이메일 요청/응답
    EMAIL_REQ_AT,
    EMAIL_RES_AT,

    // 앱푸시 요청/응답
    PUSH_REQ_AT,
    PUSH_RES_AT,

    // 사용자 정의 과금 코드
    BILL_CODE,
    ;


}
