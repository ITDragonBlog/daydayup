package com.itdragon.marshalling;

import java.io.Serializable;

public class ITDragonRespData implements Serializable {  
  
    private Long id;  
    private String name;  
    private String responseMsg;  
  
    public Long getId() {  
        return id;  
    }  
    public void setId(Long id) {  
        this.id = id;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public String getResponseMsg() {  
        return responseMsg;  
    }  
    public void setResponseMsg(String responseMsg) {  
        this.responseMsg = responseMsg;  
    }
	@Override
	public String toString() {
		return "ITDragonRespData [id=" + id + ", name=" + name + ", responseMsg=" + responseMsg + "]";
	}  
  
}  