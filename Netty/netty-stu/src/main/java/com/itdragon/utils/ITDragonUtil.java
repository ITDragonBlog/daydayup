package com.itdragon.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;  
import java.util.zip.GZIPOutputStream;

public class ITDragonUtil {
	
	private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
    public static Object cal(String expression){
        try {
			return jse.eval(expression);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    public static byte[] gzip(byte[] data){  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(bos);
			gzip.write(data);  
			gzip.finish();  
			gzip.close();  
			byte[] ret = bos.toByteArray();  
			bos.close();  
			return ret;  
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return null;
    }  
      
    public static byte[] ungzip(byte[] data){  
        ByteArrayInputStream bis = new ByteArrayInputStream(data);  
        GZIPInputStream gzip;
		try {
			gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];  
			int num = -1;  
			ByteArrayOutputStream bos = new ByteArrayOutputStream();  
			while((num = gzip.read(buf, 0 , buf.length)) != -1 ){  
				bos.write(buf, 0, num);  
			}  
			gzip.close();  
			bis.close();  
			byte[] ret = bos.toByteArray();  
			bos.flush();  
			bos.close();  
			return ret;  
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
    }  

}
