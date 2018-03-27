package com.itdragon.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itdragon.dao.ITDragonDao;

@Service
public class ITDragonServer {
	
	@Autowired(required=false)
	private ITDragonDao itdragonDao;

	@Override
	public String toString() {
		return "ITDragonServer [itdragonDao=" + itdragonDao + "]";
	}
	
}
