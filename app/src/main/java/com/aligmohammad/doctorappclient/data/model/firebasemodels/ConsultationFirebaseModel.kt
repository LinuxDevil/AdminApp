package com.aligmohammad.doctorappclient.data.model.firebasemodels

data class ConsultationFirebaseModel(
    var consultIn: String?,
    var tempreture: String?,
    var userId: String?,
    var city: String?,
    var district: String?,
    var doctorId: String?
) {
    constructor(): this("", "", "", "", "", "")
}