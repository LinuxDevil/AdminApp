package com.aligmohammad.doctorappclient.data.model.firebasemodels

data class RequestDoctorNurseFirebaseModel (
    var major: String?,
    var userId: String?,
    var citySelected: String?,
    var districtSelected: String?,
    var total: String?
) {
    constructor(): this("", "", "", "", "")
}