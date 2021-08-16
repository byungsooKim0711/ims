package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.kimbs.ims.protocol.v1.trace.TraceInfo;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImsPacket<T> {

    @JsonProperty("command")
    private ImsPacketCommand command;

    @JsonProperty("data")
    private T data;

    @JsonProperty("trace_info")
    private TraceInfo traceInfo;

//    @Builder
//    public ImsPacket(ImsPacketCommand command, T data, TraceInfo traceInfo) {
//        this.command = command;
//        this.data = data;
//        this.traceInfo = traceInfo;
//    }
}
