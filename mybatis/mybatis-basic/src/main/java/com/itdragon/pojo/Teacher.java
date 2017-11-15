package com.itdragon.pojo;

import java.io.Serializable;

public class Teacher implements Serializable{
	
	private Integer id;
	private String subject;
	
	public Teacher() {
	}

	public Teacher(Integer id, String subject) {
		this.id = id;
		this.subject = subject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", subject=" + subject + "]";
	}

}
