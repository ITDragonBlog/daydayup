package com.itdragon.pojo;

import java.io.Serializable;

public class Id implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
