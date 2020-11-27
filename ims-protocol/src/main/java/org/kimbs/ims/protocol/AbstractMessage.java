package org.kimbs.ims.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMessage implements Serializable {

    private static final long serialVersionUID = 2844860809405463902L;

    @JsonProperty("trace_info")
    private final Map<TraceInfo, String> traceInfoMap = new HashMap<>();

    public void addTraceInfo(TraceInfo key, String value) {
        traceInfoMap.put(key, value);
    }

    public void removeTraceInfo(TraceInfo key) {
        traceInfoMap.remove(key);
    }

    public String getTraceInfo(TraceInfo key) {
        return traceInfoMap.get(key);
    }
}
