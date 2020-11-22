package org.kimbs.ims.protocol.code;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.kimbs.ims.protocol.exception.NotSupportButtonType;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum ButtonType {

    // 웹링크
    WL("WL"),
    // 앱링크
    AL("AL"),
    // 배송조회
    DS("DS"),
    // 봇키워드
    BK("BK"),
    // 메시지전달
    MD("MD"),
    // 플친 보이스톡
    VT("VT"),
    // 채널 추가
    AC("AC"),
    ;

    private final String code;

    ButtonType(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    private static final Map<String, ButtonType> buttonTypeMap = Arrays
            .stream(values())
            .collect(Collectors.toMap(ButtonType::getCode, Function.identity()));

    public static ButtonType fromValue(String code) {
        return Optional
                .ofNullable(buttonTypeMap.get(code))
                .orElseThrow(() -> new NotSupportButtonType("Not support buttonType : " + code));
    }
}
