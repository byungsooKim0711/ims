package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractMessage implements Serializable {

    private static final long serialVersionUID = -6437664215015324020L;

    @JsonProperty("trace_info")
    private TraceInfo trace = new TraceInfo();
}
