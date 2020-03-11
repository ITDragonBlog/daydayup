package com.itdragon.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.*
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.server.standard.ServerEndpointExporter

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        // 设置订阅Broker名称,/topic为广播模式
        config.enableSimpleBroker("/topic")
        // 设置应用程序全局目标前缀,作用体现在@SubscribeMapping和@MessageMapping上
        config.setApplicationDestinationPrefixes("/itdragon")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // 允许使用socketJs方式访问，访问端点为socket，并允许跨域
        registry.addEndpoint("/socket").setAllowedOrigins("*").withSockJS()
    }

    // 原生WebSocket
    @Bean
    fun serverEndpointExporter(): ServerEndpointExporter {
        return ServerEndpointExporter()
    }
}