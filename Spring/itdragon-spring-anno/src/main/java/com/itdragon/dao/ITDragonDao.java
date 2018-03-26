package com.itdragon.dao;

import org.springframework.stereotype.Repository;

@Repository
public class ITDragonDao {
	
	private String version = "v1";
	
	public ITDragonDao() {
	}

	public ITDragonDao(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "ITDragonDao [version=" + version + "]";
	}
	
}
