package org.kimbs.ims.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public final class SerialNumberUtils {

    private static final AtomicInteger aiSerialNumberKey = new AtomicInteger();

    public static String generateSerialNumber(String prefix, String msgUid) {
        return new StringBuilder(prefix)
                .append("_")
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddHHmmssSSS")))
                .append((aiSerialNumberKey.incrementAndGet() % 900 + 100)).append("_")
                .append(msgUid)
                .toString();
    }
}
