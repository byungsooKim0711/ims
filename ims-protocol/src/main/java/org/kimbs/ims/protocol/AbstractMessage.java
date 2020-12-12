package org.kimbs.ims.protocol;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMessage implements Serializable {

    private static final long serialVersionUID = 2844860809405463902L;

    private final Map<TraceInfo, Object> traceInfoMap = new HashMap<>();

    public void addTraceInfo(TraceInfo key, Object value) {
        traceInfoMap.put(key, value);
    }

    public void removeTraceInfo(TraceInfo key) {
        traceInfoMap.remove(key);
    }

    public Object getTraceInfo(TraceInfo key) {
        return traceInfoMap.get(key);
    }

    public Map<TraceInfo, Object> getTraceInfo() {
        return traceInfoMap;
    }

    public void addTraceInfo(Map<TraceInfo, Object> traceInfoMap) {
        this.traceInfoMap.putAll(traceInfoMap);
    }
}
