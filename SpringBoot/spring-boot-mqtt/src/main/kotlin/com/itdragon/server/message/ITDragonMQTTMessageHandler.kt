package com.itdragon.server.message

import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler

class ITDragonMQTTMessageHandler : MessageHandler {

    private var handler: ((String) -> Unit)? = null

    fun registerHandler(handler: (String) -> Unit) {
        this.handler = handler
    }

    override fun handleMessage(message: Message<*>) {
        handler?.run { this.invoke(message.payload.toString()) }
    }
}