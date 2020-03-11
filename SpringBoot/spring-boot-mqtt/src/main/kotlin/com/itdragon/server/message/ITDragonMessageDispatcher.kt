package com.itdragon.server.message

import com.itdragon.server.config.MQTTConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Instant
import javax.annotation.PostConstruct

@Component
class ITDragonMessageDispatcher {

    private val logger = LoggerFactory.getLogger(ITDragonMessageDispatcher::class.java)

    @Autowired
    lateinit var itDragonMQTTMessageHandler: ITDragonMQTTMessageHandler
    @Autowired
    lateinit var mqttGateway: MQTTConfig.MQTTMessageGateway

    @PostConstruct
    fun init() {
        itDragonMQTTMessageHandler.registerHandler { itDragonMsgHandler(it) }
    }

    fun itDragonMsgHandler(message: String) {
        logger.info("itdragon mqtt receive message: $message")
        try {
            // todo
        }catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @Scheduled(fixedDelay = 10*1000)
    fun sendMessage() {
        mqttGateway.sendToMqtt("Hello ITDragon ${Instant.now()}", "itDragon/tags/cov/set")
    }

}