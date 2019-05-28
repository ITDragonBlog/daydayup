package com.java.helloworld;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.util.Date;

public class InstantD {

    public static void main(String[] args) {
        methodInstant();
    }

    public static void methodInstant() {
        Instant instant = Instant.now();
        System.out.println("一小时后的时刻 : " + instant.plusSeconds(3600));
        System.out.println("一小时前的时刻 : " + instant.minusSeconds(3600));
        System.out.println("时间比较 : " + instant.isAfter(instant.plusMillis(-1000)));
        Instant parseInstant = Instant.parse("2019-05-28T00:00:00Z");
        System.out.println(parseInstant);
    }

    public static void baseInstant() {
        Instant instant = Instant.now();
        Instant instantClock = Instant.now(Clock.systemDefaultZone());
        Instant instantMilli = Instant.ofEpochMilli(new Date().getTime());
        System.out.println("从系统时钟获取当前时刻 : " + instant);
        System.out.println("从指定时钟获取当前时刻 : " + instantClock);
        System.out.println("通过毫秒计算指定时刻 : " + instantMilli);
        System.out.println("将当前时刻与时区结合 : " + Instant.now().atZone(ZoneId.systemDefault()));
    }

    public static void dateToInstant() {
        System.out.println("Date to Instant : " + new Date().toInstant());
        System.out.println("Instant to Date : " + Instant.ofEpochMilli(new Date().getTime()));
        new Date(Instant.MAX.toEpochMilli());
    }

}
