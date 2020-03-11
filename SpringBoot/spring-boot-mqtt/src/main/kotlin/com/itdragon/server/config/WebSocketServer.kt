package com.itdragon.server.config

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap
import javax.websocket.*
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint


@ServerEndpoint("/nativeSocket/{clientKey}")
@Component
class WebSocketServer {

    private var logger = LoggerFactory.getLogger(WebSocketServer::class.java)
    private var session: Session? = null
    private var clientKey = ""

    @OnOpen
    fun onOpen(session: Session, @PathParam("clientKey") clientKey: String) {
        this.session = session
        this.clientKey = clientKey
        if (webSocketMap.containsKey(clientKey)) {
            webSocketMap.remove(clientKey)
            webSocketMap[clientKey] = this
        } else {
            webSocketMap[clientKey] = this
        }
        logger.info("客户端:$clientKey 连接成功")

    }

    @OnClose
    fun onClose() {
        if (webSocketMap.containsKey(clientKey)) {
            webSocketMap.remove(clientKey)
        }
        logger.warn("客户端:$clientKey 连接关闭")

    }

    @OnMessage
    fun onMessage(message: String, session: Session) {
        logger.info("客户端:$clientKey 收到消息:$message")
    }

    @OnError
    fun onError(session: Session, error: Throwable) {
        logger.error("WebSocket客户端(${this.clientKey})错误: ${error.message}")
    }

    @Throws(IOException::class)
    fun sendMessage(message: String) {
        this.session!!.basicRemote.sendText(message)
    }

    companion object {
        private val webSocketMap = ConcurrentHashMap<String, WebSocketServer>()

        @Throws(IOException::class)
        fun sendMessage(clientKey: String, message: String) {
            webSocketMap[clientKey]?.sendMessage(message)
        }

        fun getStatus(clientKey: String): Boolean? {
            return webSocketMap[clientKey]?.session?.isOpen
        }

    }
}