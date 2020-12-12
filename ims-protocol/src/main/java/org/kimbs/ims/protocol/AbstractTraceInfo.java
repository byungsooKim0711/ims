package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractTraceInfo {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("msg_uid")
    private String msgUid;

    @JsonProperty("tracking_id")
    private String trackingId;

    @JsonProperty("received_at")
    private String receivedAt;

    @JsonProperty("bill_code")
    private String billCode;
}
