package com.aligmohammad.doctorappclient.data.network.responses

data class DoctorResponse(
    val appointment_duration: Int,
    val appointments: List<String>,
    val city: String,
    val consults: List<String>,
    val created_by: String,
    val district: String,
    val id: String,
    val image_url: String,
    val insurances: List<String>,
    val lat: Int,
    val lng: Int,
    val location_name: String,
    val major: String,
    val menu_items: String,
    val name: String,
    val password: String,
    val phone: String,
    val published_at: String,
    val qualifications: String,
    val rate: Int,
    val specialization: String,
    val updated_by: String,
    val users_permissions_roles: List<String>,
    val years_of_experience: Int
)
