package com.java.helloworld;

import java.time.LocalTime;

public class LocalTimeD {

    public static void main(String[] args) {
        baseLocalTime();
        methodLocalTime();
    }

    public static void methodLocalTime() {
        /*-- 基础方法 --*/
        LocalTime time = LocalTime.now();
        System.out.println(time);
        System.out.println("时: " + time.getHour());
        System.out.println("分: " + time.getMinute());
        System.out.println("秒: " + time.getSecond());
        System.out.println("纳秒: " + time.getNano());
        System.out.println("今天已经过去多少秒: " + time.toSecondOfDay());

        /*-- 时间比较 --*/
        LocalTime time2 = LocalTime.of(23, 10, 30);
        System.out.println("time在之后: " + time.isAfter(time2));
        System.out.println("time在之前: " + time.isBefore(time2));

        /*-- 时间偏移 --*/
        System.out.println("未来十分钟: " + time.plusMinutes(10));
        System.out.println("过去一秒钟: " + time.minusSeconds(1));

        /*-- 时间设置 --*/
        System.out.println("修改小时: " + time.withHour(18));
        System.out.println("修改分钟: " + time.withMinute(59));

        /*-- 解析时间 --*/
        System.out.println("HH:mm 格式: " + LocalTime.parse("13:14"));
        System.out.println("HH:mm:ss 格式: " + LocalTime.parse("13:14:59"));
        System.out.println("HH:mm:ss:nnn 格式: " + LocalTime.parse("13:14:59.123456789"));
    }

    public static void baseLocalTime() {
        // 在默认时区中从系统时钟获取当前时间
        System.out.println("now : " +LocalTime.now());
        // 按照时分秒获得LocalTime对象，也可以加上纳秒
        System.out.println("of : " + LocalTime.of(1,2,3));
        // 将ISO_LOCAL_TIME格式的时间文本解析成LocalTime对象
        System.out.println("parse : " + LocalTime.parse("10:11:12"));
        // 将当前时间的小时修改为23
        System.out.println("with : " + LocalTime.now().withHour(23));
    }

}
