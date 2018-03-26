package com.itdragon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itdragon.server.ITDragonServer;

@Controller
public class ITDragonController {
	
	@Autowired
	private ITDragonServer itdragonServer;

	@Override
	public String toString() {
		return "ITDragonController [itdragonServer=" + itdragonServer + "]";
	}
	
}
