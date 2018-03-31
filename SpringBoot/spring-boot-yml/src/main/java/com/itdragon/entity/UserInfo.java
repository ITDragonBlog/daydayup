package com.itdragon.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户信息
 * @ConfigurationProperties : 被修饰类中的所有属性会和配置文件中的指定值（该值通过prefix找到）进行绑定
 */
@Component
@ConfigurationProperties(prefix = "userinfo")
public class UserInfo {

    private String account;
    private Integer age;
    private Boolean active;
    private Date createdDate;
    private Map<String, Object> map;
    private List<Object> list;
    private Position position;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "account='" + account + '\'' +
                ", age=" + age +
                ", active=" + active +
                ", createdDate=" + createdDate +
                ", map=" + map +
                ", list=" + list +
                ", position=" + position +
                '}';
    }
}
