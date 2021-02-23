package com.aligmohammad.doctorappclient.data.network.responses

data class PlaceResponse(
    val created_by: String,
    val date: String,
    val id: String,
    val image_url: String,
    val insurances: List<String>,
    val location_name: String,
    val name: String,
    val place_details: String,
    val published_at: String,
    val rate: Int,
    val time: String,
    val updated_by: String,
    val visitors: Int,
    val watchers: Int
)
