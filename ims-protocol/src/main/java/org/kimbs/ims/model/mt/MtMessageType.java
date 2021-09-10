package org.kimbs.ims.model.mt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.kimbs.ims.exception.NotSupportMessageType;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum MtMessageType {

    SMS("SM"),
    LMS("LM"),
    MMS("MM"),
    ;

    private final String type;

    MtMessageType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    private static final Map<String, MtMessageType> messageTypeMap = Arrays
            .stream(values())
            .collect(Collectors.toMap(MtMessageType::getType, Function.identity()));

    @JsonCreator
    public static MtMessageType fromValue(String type) {
        return Optional
                .ofNullable(messageTypeMap.get(type))
                .orElseThrow(() -> new NotSupportMessageType("Not support message_type : " + type));
    }
}
