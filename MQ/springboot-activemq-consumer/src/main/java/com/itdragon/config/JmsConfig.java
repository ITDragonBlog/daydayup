package com.itdragon.config;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@Configuration  
@EnableJms  
public class JmsConfig {  
	
    @Bean  // 开启pub/Sub模式
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {  
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();  
        factory.setPubSubDomain(true);  
        factory.setConnectionFactory(connectionFactory);  
        return factory;  
    }  
  
    @Bean  // JMS默认开启P2P模式
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {  
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();  
        factory.setPubSubDomain(false); 
        factory.setConnectionFactory(connectionFactory);  
        return factory;  
    }  
}  
