package com.aligmohammad.doctorappclient.data.model.firebasemodels

data class PharmacyOrder(
    var userId: String?,
    var placeId: String?,
    var medicine: String?,
    var pictureUrl: String?
) {
    constructor() : this("", "", "", "")
}

data class DeviceOrder(
    var userId: String?,
    var placeId: String?,
    var device: String?,
    var pictureUrl: String?
) {
    constructor() : this("", "", "", "")
}