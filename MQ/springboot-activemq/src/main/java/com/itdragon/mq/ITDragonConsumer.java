package com.itdragon.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.itdragon.utils.ITDragonUtil;

/**
 * 消息队列消费者
 * @author itdragon
 *
 */
@Service
public class ITDragonConsumer {
	
	// 1. 监听名字为"queue.name"的点对点队列
    @JmsListener(destination = "queue.name")
    public void receiveQueue(String text) {  
        System.out.println("Queue Receiver : " + text + " \t 处理结果 : " + ITDragonUtil.cal(text));  
    }  
    
    // 2. 监听名字为"topic.name"的发布订阅队列
    @JmsListener(destination = "topic.name")
    public void receiveTopic(String text) {  
    	System.out.println("Topic Receiver : " + text + " \t 处理结果 : " + ITDragonUtil.cal(text));  
    }  
    
    // 3. 监听名字为"response.name"的接收应答(双向)队列
    @JmsListener(destination = "response.name")
    @SendTo("out.queue")
    public String receiveResponse(String text) {
    	System.out.println("Response Receiver : " + text + " \t 正在返回数据......");  
    	return ITDragonUtil.cal(text).toString();
    }

}
