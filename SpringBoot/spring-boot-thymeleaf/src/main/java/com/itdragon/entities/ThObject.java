package com.itdragon.entities;

/**
 * 用于测试th:object属性实体类
 */
public class ThObject {

    private Long id;
    private String thName;
    private String desc;

    public ThObject() {
    }

    public ThObject(Long id, String thName, String desc) {
        this.id = id;
        this.thName = thName;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThName() {
        return thName;
    }

    public void setThName(String thName) {
        this.thName = thName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
