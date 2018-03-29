package com.itdragon.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ITDragonDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String,Object>> findAll() {
		String sql = "select * from itdragon_user;";
		return jdbcTemplate.queryForList(sql);
	}
	
	public void updateUserNameById(String name, Long id) {
		String sql = "update itdragon_user set user_name = ? where id = ?";  
		jdbcTemplate.update(sql, name, id);
	}
	
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
