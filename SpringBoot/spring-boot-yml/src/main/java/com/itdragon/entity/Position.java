package com.itdragon.entity;

/**
 * 用户职位
 */
public class Position {

    private String name;
    private Double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "position{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
