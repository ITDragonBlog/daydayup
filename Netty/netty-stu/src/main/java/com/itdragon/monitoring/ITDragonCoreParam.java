package com.itdragon.monitoring;

/**
 * 根据实际情况修改CLIENT_HOST 和 SALT_KEY
 */
public enum ITDragonCoreParam {
	
	PORT ("8888"),  
    SERVER_HOST ("127.0.0.1"),      // 服务器IP地址  
    CLIENT_HOST ("192.168.1.52"),	// 本机IP地址  
    AUTH_SUCCESS ("auth_success"),  
    AUTH_ERROR ("auth_error"),  
    SALT_KEY ("ITDragon"),  
    COMBINED ("combined"),          // cpu总使用率  
    USER ("user"),                  // cpu用户使用率  
    SYS ("sys"),                    // cpu系统使用率  
    WAIT ("wait"),                  // cpu等待率  
    IDLE ("idle"),                  // cpu空闲率  
    TOTAL ("total"),                // 内存总量  
    USED ("used"),                  // 当前内存使用量  
    FREE ("free");                  // 当前内存剩余量  
      
    String value;  
  
    private ITDragonCoreParam(String value) {  
        this.value = value;  
    }  
  
    public static ITDragonCoreParam fromValue(String v) {  
        for (ITDragonCoreParam c: ITDragonCoreParam.values()) {  
            if (c.value.equals(v)) {  
                return c;  
            }  
        }  
        throw new IllegalArgumentException(v);  
    }  
      
    public String getValue() {  
        return value;  
    }  
  
    public void setValue(String value) {  
        this.value = value;  
    }  

}
