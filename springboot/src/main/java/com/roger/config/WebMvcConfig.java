package com.roger.config;

import com.roger.converters.StringToDateConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 將自定義轉換器添加到格式化程序註冊表中
        registry.addConverter(new StringToDateConverter());
    }


}
