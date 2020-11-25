package org.kimbs.ims.model.kakao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum ResponseMethod {

    REALTIME("realtime"),
    PUSH("push"),
    POLLING("polling"),
    ;

    private final String code;

    ResponseMethod(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    private static final Map<String, ResponseMethod> responseMethodMap = Arrays
            .stream(values())
            .collect(Collectors.toMap(ResponseMethod::getCode, Function.identity()));

    @JsonCreator
    public static ResponseMethod fromValue(String code) {
        return Optional
                .ofNullable(responseMethodMap.get(code))
                .orElse(PUSH);
    }
}
