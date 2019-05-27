package com.java.helloworld;

import java.util.Calendar;
import java.util.Date;

public class CalendarD {
    public static void main(String[] args) {
        getCalendarTime();
        setCalendarTime();
    }

    public static void getCalendarTime() {
        Calendar calendar = Calendar.getInstance();
        // 获取年
        int year = calendar.get(Calendar.YEAR);
        // 获取月，返回的值从0~11
        int month = calendar.get(Calendar.MONTH) + 1;
        // 获取日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 获取12小时制的小时
        int hour12 = calendar.get(Calendar.HOUR);
        // 获取24小时制的小时
        int hour24 = calendar.get(Calendar.HOUR_OF_DAY);
        // 获取分
        int minute = calendar.get(Calendar.MINUTE);
        // 获取秒
        int second = calendar.get(Calendar.SECOND);
        // 星期，星期天是1
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("当前时间：" + year + "年" + month + "月" + day + "日" + hour24
                + "时" + minute + "分" + second + "秒" + "星期" + weekday);
    }

    public static void setCalendarTime() {
        Calendar calendar = Calendar.getInstance();
        // 获取昨天日期
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println("获取昨天日期 : " + calendar.getTime());
        // 设置指定日期
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        System.out.println("本月30号日期 : " + calendar.getTime());
        // Date 转 Calendar
        calendar.setTime(new Date());
        System.out.println("获取Date类的日历 : " + calendar.getTime());
    }

}
