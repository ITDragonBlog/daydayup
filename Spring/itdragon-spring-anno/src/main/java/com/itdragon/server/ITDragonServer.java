package com.itdragon.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itdragon.dao.ITDragonDao;

@Service
public class ITDragonServer {
	
	@Autowired(required=false)
	private ITDragonDao itdragonDao;

	public List<Map<String,Object>> findAll() {
		return itdragonDao.findAll();
	}
	
	@Override
	public String toString() {
		return "ITDragonServer [itdragonDao=" + itdragonDao + "]";
	}
	
}
