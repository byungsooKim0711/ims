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

    }

    private final static Map<RoundRobinKey, AtomicInteger> roundRobinMap = new HashMap<>();

    static {
        for (RoundRobinKey key : RoundRobinKey.values()) {
            roundRobinMap.put(key, new AtomicInteger());
        }
    }

    public static Optional<String> getRoundRobinValue(RoundRobinKey key, List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Optional.empty();
        }

        AtomicInteger atomicInteger = roundRobinMap.get(key);

        String value = list.get(atomicInteger.incrementAndGet() % list.size());
        roundRobinMap.put(key, atomicInteger);

        return Optional.ofNullable(value);
    }
}
