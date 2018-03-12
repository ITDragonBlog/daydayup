package com.itdragon.mq;

import java.util.ArrayList;  
import java.util.List;  
import javax.jms.Connection;  
import javax.jms.ConnectionFactory;  
import javax.jms.DeliveryMode;  
import javax.jms.Destination;  
import javax.jms.MessageProducer;  
import javax.jms.Session;  
import javax.jms.TextMessage;  
import org.apache.activemq.ActiveMQConnection;  
import org.apache.activemq.ActiveMQConnectionFactory;

import com.itdragon.utils.ITDragonUtil;
/**
 * 消息队列生产者
 * @author itdragon
 *
 */
public class ITDragonProducer {
	
	private static final String QUEUE_NAME = "ITDragon.Queue";  
	  
    public static void main(String[] args) {  
        // ConnectionFactory: 连接工厂,JMS 用它创建连接  
        ConnectionFactory connectionFactory = null;  
        // Connection: JMS 客户端到 JMS Provider 的连接  
        Connection connection = null;  
        // Session: 一个发送或接收消息的线程  
        Session session = null;  
        // Destination: 消息的目的地,消息发送给谁  
        Destination destination = null;  
        // MessageProducer: 消息生产者  
        MessageProducer producer = null;  
        try {  
            // step1 构造ConnectionFactory实例对象，需要填入 用户名, 密码 以及要连接的地址，默认端口为"tcp://localhost:61616"  
            connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,  
                    ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);   
            // step2 连接工厂创建连接对象  
            connection = connectionFactory.createConnection();  
            // step3 启动  
            connection.start();  
            // step4 获取操作连接  
            /** 
             * 第一个参数：是否设置事务 true or false。 如果设置了true，第二个参数忽略，并且需要commit()才执行 
             * 第二个参数：acknowledge模式 
             * AUTO_ACKNOWLEDGE：为自动确认，客户端发送和接收消息不需要做额外的工作。不管消息是否被正常处理。 
             * CLIENT_ACKNOWLEDGE：为客户端确认。客户端接收到消息后，必须调用acknowledge方法。jms服务器才会删除消息，如果消息处理抛出异常，我们可以不调用acknowledge方法。 
             * DUPS_OK_ACKNOWLEDGE：允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。在需要考虑资源使用时，这种模式非常有效。 
             */  
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);  
            // step5 创建一个队列到目的地  
            destination = session.createQueue(QUEUE_NAME);  
            // step6 在目的地创建一个生产者  
            producer = session.createProducer(destination);  
            // step7 生产者设置不持久化，实际根据项目决定  
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
            // step8 生产者发送信息，具体的业务逻辑  
            sendMessage(session, producer);  
            session.commit();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (null != connection) {  
                    connection.close();  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    // 具体的业务逻辑处理  
    public static void sendMessage(Session session, MessageProducer producer)  
            throws Exception {  
        List<String> texts = new ArrayList<String>();  
        texts.add("ActiveMq");  
        texts.add(" - ");  
        texts.add("快速入门");  
        texts.add(" - ");  
        texts.add("ITDragon");  
        for (String text : texts) {  
            TextMessage message = session.createTextMessage(text);  
            // 发送消息到目的地方  
            producer.send(message);  
        }  
    }  

}
