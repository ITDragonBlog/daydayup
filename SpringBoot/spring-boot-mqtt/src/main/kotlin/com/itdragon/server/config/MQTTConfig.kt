package com.itdragon.server.config

import com.itdragon.server.message.ITDragonMQTTMessageHandler
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.MessagingGateway
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.core.MessageProducer
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory
import org.springframework.integration.mqtt.core.MqttPahoClientFactory
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter
import org.springframework.integration.mqtt.support.MqttHeaders
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler
import org.springframework.messaging.handler.annotation.Header
import java.time.Instant

@Configuration
class MQTTConfig {

    @Value("\${mqtt.server}")
    lateinit var mqttServer: String
    @Value("\${mqtt.user-name}")
    lateinit var mqttUserName: String
    @Value("\${mqtt.password}")
    lateinit var mqttUserPassword: String
    @Value("\${mqtt.client-id}")
    lateinit var clientID: String
    @Value("\${mqtt.cache-number}")
    lateinit var maxMessageInFlight: String
    @Value("\${mqtt.message.topic}")
    lateinit var messageTopic: String

    /**
     * 配置DefaultMqttPahoClientFactory
     * 1. 配置基本的链接信息
     * 2. 配置maxInflight，在mqtt消息量比较大的情况下将值设大
     */
    fun mqttClientFactory(): MqttPahoClientFactory {
        val mqttConnectOptions = MqttConnectOptions()
        // 配置mqtt服务端地址
        mqttConnectOptions.serverURIs = arrayOf(mqttServer)
        mqttConnectOptions.userName = mqttUserName
        mqttConnectOptions.password = mqttUserPassword.toCharArray()
        // 配置最大不确定接收消息数量，默认值10
        mqttConnectOptions.maxInflight = maxMessageInFlight.toInt()
        val factory = DefaultMqttPahoClientFactory()
        factory.connectionOptions = mqttConnectOptions
        return factory
    }

    /**
     * 配置Inbound入站，消费者基本连接配置
     * 1. 通过DefaultMqttPahoClientFactory 初始化入站通道适配器
     * 2. 配置超时时长，默认30000毫秒
     * 3. 配置Paho消息转换器
     * 4. 配置发送数据的服务质量 0~2
     * 5. 配置订阅通道
     */
    @Bean
    fun itDragonMqttInbound(): MessageProducer {
        // 初始化入站通道适配器，使用的是Eclipse Paho MQTT客户端库
        val adapter = MqttPahoMessageDrivenChannelAdapter(clientID + Instant.now().toEpochMilli(), mqttClientFactory(), messageTopic)
        // 设置连接超时时长(默认30000毫秒)
        adapter.setCompletionTimeout(30000)
        // 配置默认Paho消息转换器(qos=0, retain=false, charset=UTF-8)
        adapter.setConverter(DefaultPahoMessageConverter())
        // 设置服务质量
        // 0 最多一次，数据可能丢失;
        // 1 至少一次，数据可能重复;
        // 2 只有一次，有且只有一次;最耗性能
        adapter.setQos(0)
        // 设置订阅通道
        adapter.outputChannel = itDragonMqttInputChannel()
        return adapter
    }

    /**
     * 配置Inbound入站，消费者订阅的消息通道
     */
    @Bean
    fun itDragonMqttInputChannel(): MessageChannel {
        return DirectChannel()
    }

    /**
     * 配置Inbound入站，消费者的消息处理器
     * 1. 使用@ServiceActivator注解，表明所修饰的方法用于消息处理
     * 2. 使用inputChannel值，表明从指定通道中取值
     * 3. 利用函数式编程的思路，解耦MessageHandler的业务逻辑
     */
    @Bean
    @ServiceActivator(inputChannel = "itDragonMqttInputChannel")
    fun commandDataHandler(): MessageHandler {
        /*return MessageHandler { message ->
            println(message.payload)
        }*/
        return ITDragonMQTTMessageHandler()
    }


    /**
     * 配置Outbound出站，出站通道适配器
     * 1. 通过MqttPahoMessageHandler 初始化出站通道适配器
     * 2. 配置异步发送
     * 3. 配置默认的服务质量
     */
    @Bean
    @ServiceActivator(inputChannel = "itDragonMqttOutputChannel")
    fun itDragonMqttOutbound(): MqttPahoMessageHandler {
        // 初始化出站通道适配器，使用的是Eclipse Paho MQTT客户端库
        val messageHandler = MqttPahoMessageHandler(clientID + Instant.now().toEpochMilli() + "_set", mqttClientFactory())
        // 设置异步发送，默认是false(发送时阻塞)
        messageHandler.setAsync(true)
        // 设置默认的服务质量
        messageHandler.setDefaultQos(0)
        return messageHandler
    }

    /**
     * 配置Outbound出站，发布者发送的消息通道
     */
    @Bean
    fun itDragonMqttOutputChannel(): MessageChannel {
        return DirectChannel()
    }

    /**
     * 对外提供推送消息的接口
     * 1. 使用@MessagingGateway注解，配置MQTTMessageGateway消息推送接口
     * 2. 使用defaultRequestChannel值，调用时将向其发送消息的默认通道
     * 3. 配置灵活的topic主题
     */
    @MessagingGateway(defaultRequestChannel = "itDragonMqttOutputChannel")
    interface MQTTMessageGateway {
        fun sendToMqtt(data: String, @Header(MqttHeaders.TOPIC) topic: String)
        fun sendToMqtt(data: String)
        fun sendToMqtt(data: String, @Header(MqttHeaders.QOS) qos: Int, @Header(MqttHeaders.TOPIC) topic: String)
    }

}