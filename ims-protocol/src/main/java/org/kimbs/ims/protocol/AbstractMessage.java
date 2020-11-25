package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class AbstractMessage implements Serializable {

    private static final long serialVersionUID = 2844860809405463902L;

    @JsonProperty("trace_info")
    private Map<TraceInfo, ?> traceInfo = new HashMap<>();
}
