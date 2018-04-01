package com.itdragon.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * YAML 语法实体类
 *
 * 切记点：
 * 一、冒号后面加空格，即 key:(空格)value
 * 二、每行参数左边空格数量决定了该参数的层级，不可乱输入。
 */

@Component
@ConfigurationProperties(prefix = "yaml")
public class YamlEntity {

    // 字面值，字符串，布尔，数值
    private String str; // 普通字符串
    private String specialStr; // 输出特殊字符串
    private String specialStr2;// 转义特殊字符串
    private Boolean flag;   // 布尔类型
    private Integer num;    // 整数
    private Double dNum;    // 小数

    // 数组，List和Set，两种写法： 第一种：-空格value，每个值占一行，需缩进对齐；第二种：[1,2,...n] 行内写法
    private List<Object> list;  // list可重复集合
    private Set<Object> set;    // set不可重复集合

    // Map和实体类，两种写法：第一种：key空格value，每个值占一行，需缩进对齐；第二种：{key: value,....} 行内写法
    private Map<String, Object> map; // Map K-V
    private List<Position> positions;  // 复合结构，集合对象

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getSpecialStr() {
        return specialStr;
    }

    public void setSpecialStr(String specialStr) {
        this.specialStr = specialStr;
    }

    public String getSpecialStr2() {
        return specialStr2;
    }

    public void setSpecialStr2(String specialStr2) {
        this.specialStr2 = specialStr2;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getdNum() {
        return dNum;
    }

    public void setdNum(Double dNum) {
        this.dNum = dNum;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Set<Object> getSet() {
        return set;
    }

    public void setSet(Set<Object> set) {
        this.set = set;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "YamlEntity{" +
                "str='" + str + '\'' +
                ", specialStr='" + specialStr + '\'' +
                ", specialStr2='" + specialStr2 + '\'' +
                ", flag=" + flag +
                ", num=" + num +
                ", dNum=" + dNum +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", positions=" + positions +
                '}';
    }
}
