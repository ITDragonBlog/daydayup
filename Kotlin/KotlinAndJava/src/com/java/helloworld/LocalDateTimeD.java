package com.java.helloworld;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateTimeD {
    public static void main(String[] args) {
        baseLocalDateTime();
        dateConversion();
        methodLocalDateTime();
    }

    public static void dateConversion() {
        // LocalDateTime to LocalDate
        System.out.println(LocalDateTime.now().toLocalDate());
        // LocalDateTime to LocalTime
        System.out.println(LocalDateTime.now().toLocalTime());
        // LocalDateTime to Instant
        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        // LocalDateTime to Date
        System.out.println(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
    }

    public static void baseLocalDateTime() {
        // 在默认时区中从系统时钟获取当前时间
        System.out.println("now : " +LocalDateTime.now());
        // 按照年月日时分秒获得LocalDateTime对象
        System.out.println("of : " + LocalDateTime.of(2019,5,30,10,0,30));
        // 将ISO_LOCAL_DATE_TIME格式的时间文本解析成LocalDateTime对象
        System.out.println("parse : " + LocalDateTime.parse("2019-05-30T10:11:12"));
        // 将当前日期时间的小时修改为0点
        System.out.println("with : " + LocalDateTime.now().withHour(0));
    }

    public static void methodLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        /*-- 基础方法 --*/
        System.out.println(dateTime);
        System.out.println("年: " + dateTime.getYear());
        System.out.println("月: " + dateTime.getMonth() + " \t 值: " + dateTime.getMonthValue());
        System.out.println("日: " + dateTime.getDayOfMonth());
        System.out.println("时: " + dateTime.getHour());
        System.out.println("分: " + dateTime.getMinute());
        System.out.println("秒: " + dateTime.getSecond());
        System.out.println("纳秒: " + dateTime.getNano());
        System.out.println("今天已经过去多少秒: " + dateTime.toLocalTime().toSecondOfDay());
        System.out.println("本月多少天: " + dateTime.toLocalDate().lengthOfMonth());
        System.out.println("今年多少天: " + dateTime.toLocalDate().lengthOfYear());
        System.out.println("周: " + dateTime.getDayOfWeek());
        System.out.println("本周第几天: " + dateTime.getDayOfWeek().getValue());
        System.out.println("今年第几天: " + dateTime.getDayOfYear());
        System.out.println("是否是闰年: " + dateTime.toLocalDate().isLeapYear());

        /*-- 日期比较 --*/
        LocalDateTime date2 = LocalDateTime.of(2019, 10, 30, 12, 30, 59);
        System.out.println("date在之后: " + dateTime.isAfter(date2));
        System.out.println("date在之前: " + dateTime.isBefore(date2));

        /*-- 日期偏移 --*/
        System.out.println("未来的十天: " + dateTime.plusDays(10));
        System.out.println("过去的一月: " + dateTime.minusMonths(1));
        System.out.println("未来十分钟: " + dateTime.plusMinutes(10));
        System.out.println("过去一秒钟: " + dateTime.minusSeconds(1));

        /*-- 时间设置 --*/
        System.out.println("修改小时: " + dateTime.withHour(18));
        System.out.println("修改分钟: " + dateTime.withMinute(59));
        System.out.println("修改年份: " + dateTime.withYear(2018));
        System.out.println("修改月份: " + dateTime.withMonth(01));
    }

}
