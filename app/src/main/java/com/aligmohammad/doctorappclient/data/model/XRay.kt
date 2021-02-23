package com.aligmohammad.doctorappclient.data.model

data class XRay(
    val test: String?,
    val date: String?,
    val time: String?,
    val patientID: String?,
    val imageUrl: String?,
    val location: String?,
    val rating: Int?,
    val watchers: Int?,
    val visitors: Int?,
    val name: String?
)