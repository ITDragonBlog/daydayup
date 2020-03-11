package com.itdragon.demo;

import java.util.Random;

import javax.jms.Queue;
import javax.jms.Topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itdragon.StartApplication;
import com.itdragon.mq.ITDragonProducer;

/**
 * @RunWith 它是一个运行器
 * @RunWith(SpringRunner.class) 表示让测试运行于Spring测试环境，不用启动spring容器即可使用Spring环境
 * @SpringBootTest(classes=StartApplication.class) 表示将StartApplication.class纳入到测试环境中，若不加这个则提示bean找不到。
 * 
 * @author itdragon
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class SpringbootStudyApplicationTests {

	@Autowired
	private ITDragonProducer producer;
	
	@Autowired
    private Queue queue;
	
//	@Autowired
//	private Topic topic;
//	
//	@Autowired
//	private Queue responseQueue;

	/**
	 * 点对点(p2p)模式测试
	 * 包含三个角色：消息队列（Queue），发送者(Sender)，接收者(Receiver)。
	 * 发送者将消息发送到一个特定的队列，队列保留着消息，直到接收者从队列中获取消息。
	 */
	/*@Test
	public void testP2P(){
		for (int i = 0; i < 10; i++) {
			String []operators = {"+","-","*","/"};
    		Random random = new Random(System.currentTimeMillis());  
    		String expression = random.nextInt(10)+operators[random.nextInt(4)]+(random.nextInt(10)+1);
			producer.sendMessage(this.queue, expression);
		}
	}*/
	
	/*@Test
	public void testPubSub() {
		for (int i = 0; i < 10; i++) {
			String []operators = {"+","-","*","/"};
    		Random random = new Random(System.currentTimeMillis());
    		String expression = random.nextInt(10)+operators[random.nextInt(4)]+(random.nextInt(10)+1);
			producer.sendMessage(this.topic, expression);
		}
	}
	
	@Test
	public void testReceiveResponse(){
		for (int i = 0; i < 10; i++) {
			String []operators = {"+","-","*","/"};
    		Random random = new Random(System.currentTimeMillis());  
    		String expression = random.nextInt(10)+operators[random.nextInt(4)]+(random.nextInt(10)+1);
			producer.sendMessage(this.responseQueue, expression);
		}
	}*/
}
