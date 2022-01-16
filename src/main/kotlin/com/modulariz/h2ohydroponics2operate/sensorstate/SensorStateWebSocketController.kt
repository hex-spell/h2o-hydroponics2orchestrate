package com.modulariz.h2ohydroponics2operate.sensorstate

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.io.IOException

@Configuration
@EnableWebSocket
class StatsMonitorWebSocketController : WebSocketConfigurer {
    @Autowired
    lateinit var sensorStateCacheRepository: SensorStateCacheRepository
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(SensorStateSocketHandler(sensorStateCacheRepository), "/ws").setAllowedOrigins("*")
    }
}

@Component
class SensorStateSocketHandler(val sensorStateCacheRepository: SensorStateCacheRepository) : TextWebSocketHandler() {
    var sessions = mutableListOf<WebSocketSession>()
    @Throws(InterruptedException::class, IOException::class)
    public override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        for (webSocketSession in sessions) {
            val sensorState = Gson().fromJson(
                message.payload,
                SensorStateDto::class.java
            )
            webSocketSession.sendMessage(message)
            sensorStateCacheRepository.save(sensorState.toRedisEntity())
        }
    }

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(session)
    }
}