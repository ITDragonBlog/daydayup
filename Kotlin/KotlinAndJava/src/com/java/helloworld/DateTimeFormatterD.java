package com.java.helloworld;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterD {

    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        /**
         * LocalDateTime parse 默认解析日期格式 2019-06-01T12:00:00，
         * 否则需要DateTimeFormatter辅助
         */
        LocalDateTime.parse("2019-06-01 12:00:00", dateTimeFormatter);
        /***
         * LocalDateTime 默认日期格式 2019-06-01T12:00:00.000
         * 通过dateTimeFormatter 指定格式
         */
        LocalDateTime.now().format(dateTimeFormatter);
        /**
         * Instant 并不能直接日期格式化
         * 先转LocalDateTime类，然后再日期格式化
         */
        LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).format(dateTimeFormatter);
    }
}
