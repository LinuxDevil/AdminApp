package com.aligmohammad.doctorappclient.data.model.firebasemodels

data class DeviceTypesFirebaseModel(
    val name_ar: String?,
    val name_en: String?,
    val image_url: String?,
    val deviceList: List<String>?,
) {
    constructor() : this("", "", "", arrayListOf())
}