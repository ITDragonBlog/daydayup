package com.itdragon.pojo;

// 学习 mybatis crud 实体类
public class Person {
    private Integer id;
    private String email;
    private String lastName; // 这里lastName 在数据库中对应的是 last_name, 这会出现：字段名与实体类属性名不相同的冲突问题
    
    public Person() {
    }
    
    public Person(Integer id, String email, String lastName) {
		this.id = id;
		this.email = email;
		this.lastName = lastName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

	@Override
	public String toString() {
		return "Person [id=" + id + ", email=" + email + ", lastName=" + lastName + "]";
	}
    
}