package com.aligmohammad.doctorappclient.data.network.responses

data class HospitalResponse(
    val appointments: String,
    val city: String,
    val created_by: String,
    val date_available: String,
    val doctors: String,
    val end_time: String,
    val haveEmergency: Boolean,
    val holidays: String,
    val id: String,
    val image_url: String,
    val insurances: List<String>,
    val isAvialable: Boolean,
    val name_ar: String,
    val name_en: String,
    val phone_number: String,
    val places: String,
    val published_at: String,
    val updated_by: String
)
