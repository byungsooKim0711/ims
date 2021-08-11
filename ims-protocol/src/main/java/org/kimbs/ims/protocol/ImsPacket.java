package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ImsPacket<T> {

    @JsonProperty("command")
    private final ImsPacketCommand command;

    @JsonProperty("data")
    private final T data;

    @JsonProperty("trace_info")
    private final TraceInfo traceInfo;

    @Builder
    public ImsPacket(ImsPacketCommand command, T data, TraceInfo traceInfo) {
        this.command = command;
        this.data = data;
        this.traceInfo = traceInfo;
    }

}
