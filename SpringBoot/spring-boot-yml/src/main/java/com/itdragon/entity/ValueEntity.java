package com.itdragon.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

/**
 * Value 注解语法类
 * 第一步：在属性上添加注解Value注入参数
 * 第二步：把Value注解修饰的类添加到Spring的IOC容器中；
 * 第三步：添加数据校验注解，检查是否支持数据校验；
 *
 * 注意点：
 * 一、nickName和createdDate在yml配置文件中，对应参数分别是中划线和下划线，用于测试其对属性名匹配的松散性
 * 二、email和iphone 测试其支持JSR303数据校验
 * 三、abilities 测试其支持复杂的数据结构
 *
 * 结论：
 * 一、createDate取值必须和yml配置文件中的参数保持一致，
 * 二、既是在iphone上添加邮箱验证注解依然可以通过测试，
 * 三、不支持复杂的数据结构，提示错误和第一条相同：IllegalArgumentException: Could not resolve placeholder 'itdragon.abilities' in value "${itdragon.abilities}"
 *
 * 若想运行成功，需要注释42行；修改44行，将ceatredDate修改为ceatred_date
 */

@Component
@Validated
public class ValueEntity {

    @Value("${itdragon.nick-name}")
    private String nickName;
    @Value("${itdragon.email}")
    private String email;
    @Email
    @Value("${itdragon.iphone}")        // 解析成功，并不支持数据校验
    private String iphone;
//    @Value("${itdragon.abilities}")     // 解析错误，并不支持复杂的数据结构
    private List<String> abilities;
//    @Value("${itdragon.ceatredDate}")   // 解析错误，并不支持松散匹配属性，必须严格一致
    private Date createdDate;

    // Value注解的强大一面：支持SpEL表达式
    @Value("#{(1+2-3)/4*5}")            // 算术运算
    private String operator;
    @Value("#{1>2 || 2 <= 3}")          // 关系运算
    private Boolean comparison;
    @Value("#{systemProperties['java.version']}") // 系统配置：os.name
    private String systemProperties;
    @Value("#{T(java.lang.Math).abs(-18)}") // 表达式
    private String mapExpression;

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Boolean getComparison() {
        return comparison;
    }

    public void setComparison(Boolean comparison) {
        this.comparison = comparison;
    }

    public String getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(String systemProperties) {
        this.systemProperties = systemProperties;
    }

    public String getMapExpression() {
        return mapExpression;
    }

    public void setMapExpression(String mapExpression) {
        this.mapExpression = mapExpression;
    }

    @Override
    public String toString() {
        return "ValueEntity{" +
                "nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", iphone='" + iphone + '\'' +
                ", abilities=" + abilities +
                ", createdDate=" + createdDate +
                ", operator='" + operator + '\'' +
                ", comparison=" + comparison +
                ", systemProperties='" + systemProperties + '\'' +
                ", mapExpression='" + mapExpression + '\'' +
                '}';
    }
}
