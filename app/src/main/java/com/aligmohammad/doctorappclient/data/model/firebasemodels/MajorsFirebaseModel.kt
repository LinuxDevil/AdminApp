package com.aligmohammad.doctorappclient.data.model.firebasemodels

data class MajorsFirebaseModel(
    val name_ar: String?,
    val name_en: String?,
    val image_url: String?,
    val doctorList: List<String>?,
) {
    constructor() : this("", "", "", arrayListOf())
}
