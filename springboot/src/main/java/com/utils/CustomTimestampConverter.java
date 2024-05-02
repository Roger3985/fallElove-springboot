package com.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class CustomTimestampConverter implements Converter<String, Timestamp> {

    private final SimpleDateFormat dateFormatWithSeconds = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private final SimpleDateFormat dateFormatWithoutSeconds = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    @Override
    public Timestamp convert(String source) {
        try {
            return new Timestamp(dateFormatWithSeconds.parse(source).getTime());
        } catch (ParseException e) {
            // Try without seconds if with seconds fails
            try {
                return new Timestamp(dateFormatWithoutSeconds.parse(source).getTime());
            } catch (ParseException e2) {
                throw new IllegalArgumentException("Unparseable date: " + source);
            }
        }
    }
}
