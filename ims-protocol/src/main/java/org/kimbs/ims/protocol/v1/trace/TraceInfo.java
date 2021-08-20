package org.kimbs.ims.protocol.v1.trace;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TraceInfo implements Serializable {

    private static final long serialVersionUID = -2313086246211220293L;

    // 유저 아이디
    @JsonProperty("customer_id")
    private Long customerId;

    // 메시지 UID
    @JsonProperty("message_id")
    private String messageId;

    // 트래킹 ID
    @JsonProperty("tracking_id")
    private String trackingId;

    // 접수 시간
    @JsonProperty("received_at")
    private LocalDateTime receivedAt;

    // 라우팅 시간
    @JsonProperty("distribution_at")
    private LocalDateTime distributionAt;

    // 라우팅 토픽
    @JsonProperty("destination_topic")
    private String destinationTopic;


    @JsonProperty("trace_at")
    private TraceAt traceAt;

    @JsonProperty("trace_ft")
    private TraceFt traceFt;

    @JsonProperty("trace_mt")
    private TraceMt traceMt;

    @JsonProperty("trace_push")
    private TracePush tracePush;

    @JsonProperty("trace_email")
    private TraceEmail traceEmail;

    // 부서코드
    @JsonProperty("bill_code")
    private String billCode;
}
