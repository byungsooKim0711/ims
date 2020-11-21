package org.kimbs.ims.protocol.code;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS("0000", "success"),

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
