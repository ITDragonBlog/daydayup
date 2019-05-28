package com.java.helloworld;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class LocalDateD {

    public static void main(String[] args) {
        methodLocalDate();
        baseLocalDate();
    }

    public static void methodLocalDate() {
        /*-- 基础方法 --*/
        LocalDate date = LocalDate.now();
        System.out.println("年: " + date.getYear());
        System.out.println("月: " + date.getMonth() + " \t 值: " + date.getMonthValue());
        System.out.println("日: " + date.getDayOfMonth());
        System.out.println("本月多少天: " + date.lengthOfMonth());
        System.out.println("今年多少天: " + date.lengthOfYear());
        System.out.println("周: " + date.getDayOfWeek());
        System.out.println("本周第几天: " + date.getDayOfWeek().getValue());
        System.out.println("今年第几天: " + date.getDayOfYear());
        System.out.println("是否是闰年: " + date.isLeapYear());

        /*-- 日期比较 --*/
        LocalDate date2 = LocalDate.of(2019, 10, 30);
        System.out.println("date在之后: " + date.isAfter(date2));
        System.out.println("date在之前: " + date.isBefore(date2));

        /*-- 日期偏移 --*/
        System.out.println("未来的十天: " + date.plusDays(10));
        System.out.println("过去的一月: " + date.minusMonths(1));

        /*-- 日期偏移 --*/
        System.out.println("修改年份: " + date.withYear(2018));
        System.out.println("修改月份: " + date.withMonth(01));
    }

    public static void baseLocalDate() {
        // 在默认时区中从系统时钟获取当前日期
        LocalDate.now();
        // 按照年月日获得LocalDate对象
        LocalDate.of(2019, 10, 20);
        // 将格式为yyyy-MM-dd格式的日期文本解析成LocalDate对象
        LocalDate.parse("2019-05-29");
        LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
    }
}
