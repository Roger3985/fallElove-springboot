package com.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class StringToTimestampConverter implements Converter<String, Timestamp> {


    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Timestamp convert(String source) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = dateFormat.parse(source);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("無效的日期格式。預期格式：yyyy-MM-dd HH:mm:ss");
        }

    }
}
