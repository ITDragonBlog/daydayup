package com.itdragon.pojo;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户实体类
 * @author itdragon
 */
//@Document(collection = "itdragon_user")  如果为了代码的通用性，建议不要使用
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private String name;
	private Integer age;
	private Address address;
	private ArrayList ability;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public ArrayList getAbility() {
		return ability;
	}
	public void setAbility(ArrayList ability) {
		this.ability = ability;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + ", ability=" + ability
				+ "]";
	}
}
