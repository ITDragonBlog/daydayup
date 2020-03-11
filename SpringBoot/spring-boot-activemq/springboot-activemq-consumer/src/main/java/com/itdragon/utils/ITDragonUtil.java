package com.itdragon.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ITDragonUtil {
	
	private final static ScriptEngine JSE = new ScriptEngineManager().getEngineByName("JavaScript");
    
	public static Object cal(String expression){
        try {
			return JSE.eval(expression);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
        return null;
    }

}
