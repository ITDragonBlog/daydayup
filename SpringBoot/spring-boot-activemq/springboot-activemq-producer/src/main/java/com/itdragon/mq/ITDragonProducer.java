package com.itdragon.mq;

import java.util.Random;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
/**
 * 消息队列生产者
 * @author itdragon
 *
 */
@Service
@EnableScheduling
public class ITDragonProducer {
	
	@Autowired
    private JmsMessagingTemplate jmsTemplate;  
	
	@Autowired
    private Queue queue;
	
	@Autowired
	private Topic topic;
	
	@Autowired
	private Queue responseQueue;
	
	/**
	 * 点对点(p2p)模式测试
	 * 包含三个角色：消息队列（Queue），发送者(Sender)，接收者(Receiver)。
	 * 发送者将消息发送到一个特定的队列，队列保留着消息，直到接收者从队列中获取消息。
	 */
	@Scheduled(fixedDelay = 5000)
	public void testP2PMQ(){
		for(int i = 0; i < 5; i++) {
			String []operators = {"+","-","*","/"};
			Random random = new Random(System.currentTimeMillis());  
			String expression = random.nextInt(10)+operators[random.nextInt(4)]+(random.nextInt(10)+1);
			jmsTemplate.convertAndSend(this.queue, expression);
			System.out.println("Queue Sender ---------> " + expression);
		}
	}
	
	/**
	 * 订阅/发布(Pub/Sub)模拟测试
	 * 包含三个角色：主题（Topic），发布者（Publisher），订阅者（Subscriber） 。
	 * 多个发布者将消息发送到Topic,系统将这些消息传递给多个订阅者。
	 */
	@Scheduled(fixedDelay = 5000)
	public void testPubSubMQ() {
		for (int i = 0; i < 5; i++) {
			String []operators = {"+","-","*","/"};
    		Random random = new Random(System.currentTimeMillis());
    		String expression = random.nextInt(10)+operators[random.nextInt(4)]+(random.nextInt(10)+1);
    		jmsTemplate.convertAndSend(this.topic, expression);
	    	System.out.println("Topic Sender ---------> " + expression);
		}
	}
	
	/**
	 * 双向应答模式测试
	 * P2P和Pub/Sub是MQ默认提供的两种模式，而双向应答模式则是在原有的基础上做了改进。发送者既是接收者，接收者也是发送者。
	 */
	@Scheduled(fixedDelay = 5000)
	public void testReceiveResponseMQ(){
		for (int i = 0; i < 5; i++) {
			String []operators = {"+","-","*","/"};
    		Random random = new Random(System.currentTimeMillis());  
    		String expression = random.nextInt(10)+operators[random.nextInt(4)]+(random.nextInt(10)+1);
    		jmsTemplate.convertAndSend(this.responseQueue, expression);
		}
	}
    
	// 接收P2P模式，消费者返回的数据
    @JmsListener(destination = "out.queue")
    public void receiveResponse(String message) {  
    	System.out.println("Producer Response Receiver  ---------> " + message);  
    }

}
