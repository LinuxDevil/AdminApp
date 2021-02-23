package com.aligmohammad.doctorappclient.data.model.firebasemodels

data class CityDistrict (
    var name_en: String?,
    var name_ar: String?,
    var districts: List<String>?
) {
    constructor(): this("", "", listOf())
}