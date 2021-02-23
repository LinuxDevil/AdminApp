package com.aligmohammad.doctorappclient.data.model

import android.os.Parcel
import android.os.Parcelable
import com.aligmohammad.doctorappclient.data.network.responses.MenuItemResponseItem
import java.util.ArrayList

data class Doctor(
    var uuid: String?,
    var phone: String?,
    val name: String?,
    var username: String?,
    val email: String?,
    val password: String?,
    val imageUrl: String?,
    var city: String?,
    var district: String?,
    var insuranceCompany: String?,
    var appointments: List<String>?,
    var appointmentsHistory: List<String>?,
    var currentAppointment: String?,
    var consultation: List<String>?,
    var appointmentDuration: String?,
    var shifts: List<String>?,
    var times: List<String>?,
    var dates: List<String>?,
    var consultationHistory: List<String>?,
    val hospital: String?,
    var menuItems: ArrayList<MenuItemResponseItem>?,
    val rating: Int?,
    val major: String?,
    val yearsOfExperience: Int?,
    val qualificationsUrl: List<String>?,
    val relatedInsurances: List<String>?,
    val locationName: String?,
    val locationLat: Float?,
    val locationLng: Float?,

    ): Parcelable  {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.createTypedArrayList(MenuItemResponseItem),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Float::class.java.classLoader) as? Float
    ) {
    }

    constructor(): this("","", "", "", "", "", "", "", "",  "", null, null, null, null, "", null, null, null, null, "", arrayListOf(), 0, null, 0, null, null, null, null, null)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uuid)
        parcel.writeString(phone)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(imageUrl)
        parcel.writeString(city)
        parcel.writeString(district)
        parcel.writeString(insuranceCompany)
        parcel.writeStringList(appointments)
        parcel.writeStringList(appointmentsHistory)
        parcel.writeString(currentAppointment)
        parcel.writeStringList(consultation)
        parcel.writeString(appointmentDuration)
        parcel.writeStringList(shifts)
        parcel.writeStringList(times)
        parcel.writeStringList(dates)
        parcel.writeStringList(consultationHistory)
        parcel.writeString(hospital)
        parcel.writeTypedList(menuItems)
        parcel.writeValue(rating)
        parcel.writeString(major)
        parcel.writeValue(yearsOfExperience)
        parcel.writeStringList(qualificationsUrl)
        parcel.writeStringList(relatedInsurances)
        parcel.writeString(locationName)
        parcel.writeValue(locationLat)
        parcel.writeValue(locationLng)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Doctor> {
        override fun createFromParcel(parcel: Parcel): Doctor {
            return Doctor(parcel)
        }

        override fun newArray(size: Int): Array<Doctor?> {
            return arrayOfNulls(size)
        }
    }
}