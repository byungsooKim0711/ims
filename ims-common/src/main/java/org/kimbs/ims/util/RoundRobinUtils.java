package org.kimbs.ims.util;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public final class RoundRobinUtils {

    @Getter
    public enum RoundRobinKey {

        RECV_AT,
        RECV_FT,
        RECV_BT,
        RECV_EMAIL,
        RECV_PUSH,
        RECV_MT,

        SEND_AT,
        SEND_FT,
        SEND_BT,
        SEND_EMAIL,
        SEND_PUSH,
        SEND_SMS,
        SEND_LMS,
        SEND_MMS,

        REPORT_AT,
        REPORT_FT,
        REPORT_BT,
        REPORT_EMAIL,
        REPORT_PUSH,
        REPORT_MT,

        HIST_AT,
        HIST_FT,
        HIST_BT,
        HIST_MT,
        HIST_EMAIL,
        HIST_PUSH,

        COMMON_LOG,
        ;
    }

    private final static Map<RoundRobinKey, AtomicInteger> roundRobinMap = new HashMap<>();

    static {
        for (RoundRobinKey key : RoundRobinKey.values()) {
            roundRobinMap.put(key, new AtomicInteger());
        }
    }

    public static String getRoundRobinValue(RoundRobinKey key, List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return key.name();
        }

        AtomicInteger atomicInteger = roundRobinMap.get(key);

        String value = list.get(atomicInteger.incrementAndGet() % list.size());
        roundRobinMap.put(key, atomicInteger);

        return value;
    }
}
