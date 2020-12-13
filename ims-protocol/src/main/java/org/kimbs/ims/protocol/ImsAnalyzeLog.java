package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImsAnalyzeLog {

    @JsonProperty("date")
    private LocalDateTime date;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("phone_number]")
    private String phoneNumber;

    @JsonProperty("message")
    private String message;
}
