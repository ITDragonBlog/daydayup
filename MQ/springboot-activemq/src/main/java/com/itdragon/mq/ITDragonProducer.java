package com.itdragon.mq;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.itdragon.utils.ITDragonUtil;
/**
 * 消息队列生产者
 * @author itdragon
 *
 */
@Service
public class ITDragonProducer {
	
	@Autowired
    private JmsMessagingTemplate jmsTemplate;  
	
	// 生产者发送数据
    public void sendMessage(Queue queue, final String message){  
        jmsTemplate.convertAndSend(queue, message);
        System.out.println("Queue Sender ---------> " + message);
    }  
    
    public void sendMessage(Topic topic, final String message){  
    	jmsTemplate.convertAndSend(topic, message);
    	System.out.println("Topic Sender ---------> " + message);
    } 
    
    @JmsListener(destination = "out.queue")
    public void receiveResponse(String message) {  
    	System.out.println("Producer Response Receiver : " + message);  
    }

}
