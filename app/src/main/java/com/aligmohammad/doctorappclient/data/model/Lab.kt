package com.aligmohammad.doctorappclient.data.model

data class Lab(
    val patientId: String?,
    val testName: String?,
    val date: String?,
    val time: String?,
    val imageUrl: String?,
    val location: String?,
    val rating: Int?,
    val watchers: Int?,
    val visitors: Int?,
    val name: String?
)