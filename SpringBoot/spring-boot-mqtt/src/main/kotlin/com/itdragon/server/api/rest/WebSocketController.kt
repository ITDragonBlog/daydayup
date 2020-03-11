package com.itdragon.server.api.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.annotation.SubscribeMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Instant

@Controller
class WebSocketController {

    @Autowired
    lateinit var simpMessagingTemplate: SimpMessagingTemplate

    /**
     * 订阅广播，服务器主动推给连接的客户端
     * 通过Http请求的方式触发订阅操作
     */
    @RequestMapping("/subscribeTopic")
    fun subscribeTopicByHttp() {
        while (true) {
            val channel = "/topic/subscribeTopic" // 可以灵活设置成通道
            simpMessagingTemplate.convertAndSend(channel, Instant.now())
            simpMessagingTemplate.convertAndSend("${channel}1", Instant.now().toEpochMilli())
            Thread.sleep(500)
        }
    }

    /**
     * 订阅广播，服务器主动推给连接的客户端
     * 通过Websocket的subscribe操作触发订阅操作
     */
    @SubscribeMapping("/subscribeTopic")
    fun subscribeTopicByWebSocket(): Long {
        return Instant.now().toEpochMilli()
    }

    /**
     * 服务端接收客户端发送的消息，类似OnMessage方法
     */
    @MessageMapping("/sendToServer")
    fun handleMessage(message: String) {
        println("message:{$message}")
    }

    /**
     * 将客户端发送的消息广播出去
     */
    @MessageMapping("/sendToTopic")
    @SendTo("/topic/subscribeTopic")
    fun sendToTopic(message: String): String {
        return message
    }

}
