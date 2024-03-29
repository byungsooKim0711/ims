package org.kimbs.ims.protocol.code;

import lombok.Getter;

@Getter
public enum ResponseCode {
    // TODO: 오류코드 정의...
    SUCCESS("0000", "Success"),

    UNKNOWN_SERVICE_KEY_EXCEPTION("1100", "Unknown service key exception"),
    BAD_REQUEST("1400", "Bad request"),

    TOO_LONG_MESSAGE_EXCEPTION("2010", "Too long message"),
    MANDATORY_EXCEPTION("2020", "Mandatory check exception"),
    IMS_DUPLICATE_MSG_UID_EXCEPTION("2030", "Duplicate msg uid exception."),

    NOT_SUPPORT_BUTTON_TYPE_EXCEPTION("4000", "Not support button type exception"),
    NOT_SUPPORT_MESSAGE_TYPE_EXCEPTION("4001", "Not support message_type exception"),
    ATTACHMENT_EXCEPTION("4002", "Attachment exception"),
    SUPPLEMENT_EXCEPTION("4003", "Supplement exception"),

    SERVICE_SERVER_ERROR("9998", "담당자가 확인중입니다."),
    SERVICE_SERVER_ETC_ERROR("9999", "담당자가 확인중입니다.")
    ;

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
