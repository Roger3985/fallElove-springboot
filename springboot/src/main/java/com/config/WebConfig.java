package com.config;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.core.convert.converter.Converter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 添加自定義日期轉換器
        registry.addConverter(new Converter<String, Timestamp>() {
            @Override
            public Timestamp convert(String source) {
                try {
                    // 定義日期格式
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parsedDate = sdf.parse(source);
                    return new Timestamp(parsedDate.getTime());
                } catch (Exception e) {
                    throw new IllegalArgumentException("無效的日期格式。預期格式：yyyy-MM-dd HH:mm:ss", e);
                }
            }
        });
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 設置自定義日期編輯器
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(sdf, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
}
