package org.kimbs.ims.store.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter
public final class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, String> {

    private final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public String convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute.format(DateTimeFormatter.ofPattern(DEFAULT_FORMAT));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dbData) {
        return LocalDateTime.parse(dbData, DateTimeFormatter.ofPattern(DEFAULT_FORMAT));
    }
}
