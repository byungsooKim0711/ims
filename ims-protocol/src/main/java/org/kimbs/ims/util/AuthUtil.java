package org.kimbs.ims.util;


import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

public class AuthUtil {

    /**
     *
     */
    public static String getAuthKey(String authorization) {
        if (StringUtils.isEmpty(authorization)) {
            return "";
        }

        return authorization.replace("App ", "").trim();
    }

    public static String encodeApiKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }

        return new String(Base64.getEncoder().encode(key.getBytes()));
    }

    public static String decodeApiKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }

        return new String(Base64.getDecoder().decode(key));
    }

    public static String getUsername(String decodedKey, String delimiter) {
        if (!decodedKey.contains(delimiter)) {
            return decodedKey;
        }

        String[] keys = decodedKey.split(delimiter);
        if (keys.length < 2) {
            return decodedKey;
        }

        return keys[1];
    }

    public static String getSecretKey(String decodedKey, String delimiter) {
        if (!decodedKey.contains(delimiter)) {
            return decodedKey;
        }

        return decodedKey.split(delimiter)[0];
    }
}
