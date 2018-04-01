package com.itdragon.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

/**
 * ConfigurationProperties 注解语法类
 * 第一步：导入依赖 spring-boot-configuration-processor；
 * 第二步：把ConfigurationProperties注解修饰的类添加到Spring的IOC容器中；
 * 第三步：设置prefix属性，指定需要注入属性的前缀；
 * 第四步：添加数据校验注解，开启数据校验；
 *
 * 注意点：
 * 一、nickName和createdDate在yml配置文件中，对应参数分别是中划线和下划线，用于测试其对属性名匹配的松散性
 * 二、email和iphone 测试其支持JSR303数据校验
 * 三、abilities 测试其支持复杂的数据结构
 *
 * 若想运行成功，需要注释33行
 */

@Component
@ConfigurationProperties(prefix = "itdragon")
@Validated
public class ConfigurationPropertiesEntity {

    private String nickName;    // 解析成功，支持松散匹配属性
    private String email;
//    @Email                      // 解析失败，数据校验成功：BindValidationException: Binding validation errors on itdragon
    private String iphone;
    private List<String> abilities;
    private Date createdDate;   // 解析成功，支持松散匹配属性

//    @ConfigurationProperties("#{(1+2-3)/4*5}")
    private String operator;    // 语法报错，不支持SpEL表达式：not applicable to field

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "ConfigurationPropertiesEntity{" +
                "nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", iphone='" + iphone + '\'' +
                ", abilities=" + abilities +
                ", createdDate=" + createdDate +
                '}';
    }
}
