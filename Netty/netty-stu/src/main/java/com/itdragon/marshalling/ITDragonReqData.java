package com.itdragon.marshalling;

import java.io.Serializable;
import java.util.Arrays;

public class ITDragonReqData implements Serializable {  
  
	private static final long serialVersionUID = 1L;
	private Long id;  
    private String name;  
    private String requestMsg;  
    private byte[] attachment;
  
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
    public String getRequestMsg() {  
        return requestMsg;  
    }  
    public void setRequestMsg(String requestMsg) {  
        this.requestMsg = requestMsg;  
    }  
    public byte[] getAttachment() {  
        return attachment;  
    }  
    public void setAttachment(byte[] attachment) {  
        this.attachment = attachment;  
    }
	@Override
	public String toString() {
		return "ITDragonReqData [id=" + id + ", name=" + name + ", requestMsg=" + requestMsg + ", attachment="
				+ Arrays.toString(attachment) + "]";
	}  
  
}  