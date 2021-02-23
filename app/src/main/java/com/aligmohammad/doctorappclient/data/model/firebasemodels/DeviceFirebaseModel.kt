package com.aligmohammad.doctorappclient.data.model.firebasemodels

import android.os.Parcel
import android.os.Parcelable

data class DeviceFirebaseModel (
    var name: String?,
    var phone: String?,
    var price: String?,
    var location: String?,
    var city: String?,
    var district: String?,
    var orders: List<String>?,
    var imageUrl: String?,
    var uuid: String?,
    var type: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    constructor(): this("","","","","","", null, "","", "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(price)
        parcel.writeString(location)
        parcel.writeString(city)
        parcel.writeString(district)
        parcel.writeStringList(orders)
        parcel.writeString(imageUrl)
        parcel.writeString(uuid)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DeviceFirebaseModel> {
        override fun createFromParcel(parcel: Parcel): DeviceFirebaseModel {
            return DeviceFirebaseModel(parcel)
        }

        override fun newArray(size: Int): Array<DeviceFirebaseModel?> {
            return arrayOfNulls(size)
        }
    }
}