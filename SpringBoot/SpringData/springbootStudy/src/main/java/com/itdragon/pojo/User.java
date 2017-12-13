package com.itdragon.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="itdragon_user")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String account;
	private String userName;
	private String iphone;
	private String email;
	private String createdDate;
	private String udpatedDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIphone() {
		return iphone;
	}
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUdpatedDate() {
		return udpatedDate;
	}
	public void setUdpatedDate(String udpatedDate) {
		this.udpatedDate = udpatedDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", userName=" + userName + ", iphone=" + iphone + ", email="
				+ email + ", createdDate=" + createdDate + ", udpatedDate=" + udpatedDate + "]";
	}
	
}
