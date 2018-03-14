package com.itdragon.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bean配置管理类 
 * @author itdragon
 *
 */
@Configuration
public class ActiveMQBeansConfig {
	
	@Bean	// 定义一个名字为queue.name的点对点列队
	public Queue queue() {
		return new ActiveMQQueue("queue.name");
	}
	
	@Bean	// 定义一个名字为topic.name的主题队列
	public Topic topic() {
		return new ActiveMQTopic("topic.name");
	}
	
	@Bean	// 定义一个名字为response.name的双向应答队列
	public Queue responseQueue() {
		return new ActiveMQQueue("response.name");
	}
	
}
