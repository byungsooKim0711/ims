package org.kimbs.ims.util;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;

public class ByteUtil {

    public static int getByteLength(String str, Charsets charsets) {
        return StringUtils.getBytes(str, charsets.getCharset()).length;
    }

    @Getter
    public enum Charsets {
        UTF_8(Charset.defaultCharset()),
        EUC_KR(Charset.forName("euc-kr")),
        ;

        Charset charset;

        Charsets(Charset charset) {
            this.charset = charset;
        }
    }
}
