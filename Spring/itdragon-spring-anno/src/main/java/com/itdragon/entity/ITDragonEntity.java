package com.itdragon.entity;

public class ITDragonEntity {
	
	private String account;
	private String password;
	private Integer age;
	
	public ITDragonEntity() {
	}
	public ITDragonEntity(String account, String password, Integer age) {
		this.account = account;
		this.password = password;
		this.age = age;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "ITDragonEntity [account=" + account + ", password=" + password + ", age=" + age + "]";
	}
	
}
