package com.mihaidinu.newsapp.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {
    private static final DateTimeFormatter FORMATTER_WITH_OFFSET = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private static final DateTimeFormatter FORMATTER_WITHOUT_OFFSET = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        String dateString = jsonParser.getText();
        try {
            return OffsetDateTime.parse(dateString, FORMATTER_WITH_OFFSET);
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(dateString, FORMATTER_WITHOUT_OFFSET).atOffset(ZoneOffset.UTC);
        }
    }
}
