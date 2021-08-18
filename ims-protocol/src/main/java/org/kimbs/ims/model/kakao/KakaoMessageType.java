package org.kimbs.ims.model.kakao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.kimbs.ims.exception.NotSupportButtonType;
import org.kimbs.ims.exception.NotSupportMessageType;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum KakaoMessageType {

    // 알림톡 텍스트
    AT("AT"),

    // 알림톡 이미지
    AI("AI"),
    
    // 친구톡 텍스트
    FT("FT"),

    // 친구톡 이미지
    FI("FI"),

    // 친구톡 와이드 이미지
    FW("FW"),
    
    // 브랜드톡 이미지
    BI("BI"),
    
    // 브랜드톡 와이드
    BW("BW"),
    ;

    private final String type;

    KakaoMessageType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    private static final Map<String, KakaoMessageType> messageTypeMap = Arrays
            .stream(values())
            .collect(Collectors.toMap(KakaoMessageType::getType, Function.identity()));

    @JsonCreator
    public static KakaoMessageType fromValue(String type) {
        return Optional
                .ofNullable(messageTypeMap.get(type))
                .orElseThrow(() -> new NotSupportMessageType("Not support message_type : " + type));
    }

}
