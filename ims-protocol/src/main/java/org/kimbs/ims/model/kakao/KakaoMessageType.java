package org.kimbs.ims.model.kakao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.kimbs.ims.exception.NotSupportButtonType;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum KakaoMessageType {

    // 알림톡
    AT("AT"),
    
    // 친구톡
    FT("FT"),
    
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
                .orElseThrow(() -> new NotSupportButtonType("Not support buttonType : " + type));
    }

}
