package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.kimbs.ims.protocol.v1.trace.TraceInfo;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImsPacket<T /*extends AbstractMessage*/> {

    // TODO: <T extends AbstractMessage> 사용했을 때 안되는 이유가 뭘까 (no creator, blah blah~)

    @JsonProperty("command")
    private ImsPacketCommand command;

    @JsonProperty("data")
    private T data;

    @JsonProperty("trace_info")
    private TraceInfo traceInfo;
}
