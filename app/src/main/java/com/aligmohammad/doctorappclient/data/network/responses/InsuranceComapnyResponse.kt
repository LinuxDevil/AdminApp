package com.aligmohammad.doctorappclient.data.network.responses

class InsuranceCompanyResponse : ArrayList<InsuranceCompanyResponseItem>()

data class InsuranceCompanyResponseItem(
    val doctors: List<DoctorResponse>,
    val firebase_users: List<FirebaseUserResponse>,
    val hospitals: List<HospitalResponse>,
    val id: String,
    val image_url: String,
    val isAvailable: Boolean,
    val name_ar: String,
    val name_en: String,
    val places: List<PlaceResponse>,
    val published_at: String
)
