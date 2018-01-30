package com.itdragon.marshalling;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
import org.jboss.marshalling.MarshallerFactory;  
import org.jboss.marshalling.Marshalling;  
import org.jboss.marshalling.MarshallingConfiguration; 

public final class ITDragonMarshallerFactory {
	private static final String NAME = "serial"; // serial表示创建的是 Java序列化工厂对象.由jboss-marshalling-serial提供 
    private static final Integer VERSION = 5;  
    private static final Integer MAX_OBJECT_SIZE = 1024 * 1024 * 1; // 单个对象最大长度 
      
    /** 
     * 创建Jboss Marshalling 解码器MarshallingDecoder 
     * @return MarshallingDecoder 
     */  
    public static MarshallingDecoder buildMarshallingDecoder() {  
        // step1 通过工具类 Marshalling，获取Marshalling实例对象，参数serial 标识创建的是java序列化工厂对象  
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory(NAME);  
        // step2 初始化Marshalling配置  
        final MarshallingConfiguration configuration = new MarshallingConfiguration();  
        // step3 设置Marshalling版本号  
        configuration.setVersion(VERSION);  
        // step4 初始化生产者  
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);  
        // step5 通过生产者和单个消息序列化后最大长度构建 Netty的MarshallingDecoder  
        MarshallingDecoder decoder = new MarshallingDecoder(provider, MAX_OBJECT_SIZE);  
        return decoder;  
    }  
      
    /** 
     * 创建Jboss Marshalling 编码器MarshallingEncoder 
     * @return MarshallingEncoder 
     */  
    public static MarshallingEncoder builMarshallingEncoder() {  
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory(NAME);  
        final MarshallingConfiguration configuration = new MarshallingConfiguration();  
        configuration.setVersion(VERSION);  
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);  
        MarshallingEncoder encoder = new MarshallingEncoder(provider);  
        return encoder;  
    }  
}
