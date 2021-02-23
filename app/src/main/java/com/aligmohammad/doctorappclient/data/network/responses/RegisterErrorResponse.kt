package com.aligmohammad.doctorappclient.data.network.responses

data class RegisterErrorResponse(
    val `data`: List<Data>,
    val error: String,
    val message: List<MessageX>,
    val statusCode: Int
)

data class Data(
    val messages: List<Message>
)

data class MessageX(
    val messages: List<MessageXX>
)

data class Message(
    val id: String,
    val message: String
)

data class MessageXX(
    val id: String,
    val message: String
)