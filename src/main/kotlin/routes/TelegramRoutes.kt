package com.example.routes

import com.example.models.Update
import com.example.services.TelegramService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.telegramRoutes() {
    routing {
        post("/webhook") {
            val update = call.receive<Update>()

            // Обработка сообщения
            update.message?.let { message ->
                val chatId = message.chat.id
                val text = message.text ?: ""

                TelegramService.sendMessage(chatId, "Вы написали: $text")
            }

            call.respondText("ok")
        }

        get("/") {
            call.respondText("Бот вроде работает")
        }
    }
}
