package com.aligmohammad.doctorappclient.data.network.responses

data class LoginResponse(
    val jwt: String,
    val user: User
)
