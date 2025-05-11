package com.example.services

import com.example.models.SendMessageRequest
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import com.typesafe.config.ConfigFactory
import io.ktor.serialization.kotlinx.json.json


object TelegramService {
    private val config = ConfigFactory.load()
    private val botToken = config.getString("bot.token")
    private val apiUrl = "https://api.telegram.org/bot$botToken"

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun sendMessage(chatId: Long, text: String) {
        val payload = SendMessageRequest(chat_id = chatId, text = text)
        client.post("$apiUrl/sendMessage") {
            contentType(ContentType.Application.Json)
            setBody(payload)
        }
    }
}
