package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import org.kimbs.ims.protocol.v1.trace.TraceInfo;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImsPacket<T> {

    @JsonProperty("command")
    private ImsPacketCommand command;

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
    @JsonProperty("data")
    private T data;

    @JsonProperty("trace_info")
    private TraceInfo traceInfo;

    public void updateCommand(ImsPacketCommand command) {
        this.command = command;
    }

    public void updateData(T data) {
        this.data = data;
    }

    public void updateTraceInfo(TraceInfo traceInfo) {
        this.traceInfo = traceInfo;
    }
}
