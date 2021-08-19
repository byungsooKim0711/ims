package org.kimbs.ims.protocol.code;

import lombok.Getter;

@Getter
public enum BizReportCode {

    SUCCESS("0000"),
    // ...
    NO_SEND_AVAILABLE_EXCEPTION("3018"),
    // ...
    NO_MATCHED_TEMPLATE_BUTTON_EXCEPTION("3027"),
    // ...
    KAKAO_ERROR1("9998"),
    KAKAO_ERROR2("9999")
    ;

    private final String code;

    BizReportCode(String code) {
        this.code = code;
    }
}
