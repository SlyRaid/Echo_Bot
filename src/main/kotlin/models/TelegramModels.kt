package com.example.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Update(
    @SerialName("update_id") val updateId: Long,
    val message: Message? = null
)

@Serializable
data class Message(
    val message_id: Long,
    val text: String? = null,
    val chat: Chat
)

@Serializable
data class Chat(
    val id: Long
)

@Serializable
data class SendMessageRequest(
    val chat_id: Long,
    val text: String
)