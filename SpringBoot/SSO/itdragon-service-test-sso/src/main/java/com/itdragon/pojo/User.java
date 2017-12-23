package com.itdragon.pojo;

public class User {
	
	private Long id;						// 自增长主键
	private String account;					// 登录的账号
	private String userName;				// 注册的昵称
	private String plainPassword; 			// 登录时的密码，不持久化到数据库
	private String password;				// 加密后的密码
	private String salt;					// 用于加密的盐
	private String iphone;					// 手机号
	private String email;					// 邮箱
	private String platform;				// 用户来自的平台
	private String createdDate;				// 用户注册时间
	private String updatedDate;				// 用户最后一次登录时间
	
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
	public String getPlainPassword() {
		return plainPassword;
	}
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
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
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", userName=" + userName + ", plainPassword=" + plainPassword
				+ ", password=" + password + ", salt=" + salt + ", iphone=" + iphone + ", email=" + email
				+ ", platform=" + platform + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
}
