package com.roger.member.converters;

import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToDateConverter implements Converter<String, Date> {

    // 設置日期格式
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String source) {
        try {
            // 將 String 轉換為 java.util.Date
            java.util.Date utilDate = dateFormat.parse(source);
            // 將 java.util.Date 轉換為 java.sql.Date
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            // 如果轉換失敗，可以選擇打印錯誤或返回 null
            e.printStackTrace();
            System.out.println("轉換失敗");
            return null;
        }
    }
}
