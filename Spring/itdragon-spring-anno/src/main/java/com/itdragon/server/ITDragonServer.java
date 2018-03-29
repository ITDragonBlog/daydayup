package com.itdragon.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itdragon.dao.ITDragonDao;

@Service
public class ITDragonServer {
	
	@Autowired(required=false)
	private ITDragonDao itdragonDao;

	public List<Map<String,Object>> findAll() {
		return itdragonDao.findAll();
	}
	
	@Transactional
	public void updateNameById(String name, Long id) {
		itdragonDao.updateUserNameById(name, id);
		System.out.println(0/0); // 事务异常
	}
	
	@Override
	public String toString() {
		return "ITDragonServer [itdragonDao=" + itdragonDao + "]";
	}
	
}
