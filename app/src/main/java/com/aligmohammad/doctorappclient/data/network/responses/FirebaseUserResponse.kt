package com.aligmohammad.doctorappclient.data.network.responses


data class FirebaseUserResponse(
    var uuid: String?,
    var email: String?,
    var username: String?,
    var phone: String?,
    var name: String?,
    var city: String?,
    var district: String?,
    var insuranceCompany: String?,
    var insuranceNumber: String?,
    var appointments: List<String>?,
    var consultation: List<String>?,
    var requests: List<String>?,
    var orders: List<String>?
) {

    constructor() : this("", "", "", "", "", "", "", null, null, null,null, null, null)

}

// User has appointments, insurance company,