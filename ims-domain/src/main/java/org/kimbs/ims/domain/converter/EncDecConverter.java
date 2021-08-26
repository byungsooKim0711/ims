package org.kimbs.ims.domain.converter;

import org.kimbs.ims.util.EncDecUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EncDecConverter implements AttributeConverter<String, String> {

    // encrypt
    @Override
    public String convertToDatabaseColumn(String s) {
        return EncDecUtil.encrypt(s);
    }

    // decrypt
    @Override
    public String convertToEntityAttribute(String s) {
        return EncDecUtil.decrypt(s);
    }
}
