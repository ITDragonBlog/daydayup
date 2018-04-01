package com.itdragon.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 随机数和占位符语法类
 */

@Component
@ConfigurationProperties(prefix = "ran")
public class RandomEntity {

    private String ranValue;    // 随机生成一个字符串
    private Integer ranInt;     // 随机生成一个整数
    private Long ranLong;       // 随机生成一个长整数
    private Integer ranIntNum;  // 在指定范围内随机生成一个整数
    private Integer ranIntRange;// 在指定区间内随机生成一个整数
    private String ranPlaceholder;// 占位符

    public String getRanValue() {
        return ranValue;
    }

    public void setRanValue(String ranValue) {
        this.ranValue = ranValue;
    }

    public Integer getRanInt() {
        return ranInt;
    }

    public void setRanInt(Integer ranInt) {
        this.ranInt = ranInt;
    }

    public Long getRanLong() {
        return ranLong;
    }

    public void setRanLong(Long ranLong) {
        this.ranLong = ranLong;
    }

    public Integer getRanIntNum() {
        return ranIntNum;
    }

    public void setRanIntNum(Integer ranIntNum) {
        this.ranIntNum = ranIntNum;
    }

    public Integer getRanIntRange() {
        return ranIntRange;
    }

    public void setRanIntRange(Integer ranIntRange) {
        this.ranIntRange = ranIntRange;
    }

    public String getRanPlaceholder() {
        return ranPlaceholder;
    }

    public void setRanPlaceholder(String ranPlaceholder) {
        this.ranPlaceholder = ranPlaceholder;
    }

    @Override
    public String toString() {
        return "RandomEntity{" +
                "ranValue='" + ranValue + '\'' +
                ", ranInt=" + ranInt +
                ", ranLong=" + ranLong +
                ", ranIntNum=" + ranIntNum +
                ", ranIntRange=" + ranIntRange +
                ", ranPlaceholder='" + ranPlaceholder + '\'' +
                '}';
    }
}