package com.aligmohammad.doctorappclient.data.model.firebasemodels

import android.os.Parcel
import android.os.Parcelable

data class OfferFirebaseModel(
    var offerName: String?,
    var offerDescription: String?,
    var city: String?,
    var district: String?,
    var beginDate: String?,
    var endDate: String?,
    var beginTime: String?,
    var endTime: String?,
    var imageUrl: String?,
    var isAvailable: Boolean
): Parcelable {
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
        parcel.readByte() != 0.toByte()
    ) {
    }

    constructor(): this("" ,"", "","", "", "", "", "","", false)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(offerName)
        parcel.writeString(offerDescription)
        parcel.writeString(city)
        parcel.writeString(district)
        parcel.writeString(beginDate)
        parcel.writeString(endDate)
        parcel.writeString(beginTime)
        parcel.writeString(endTime)
        parcel.writeString(imageUrl)
        parcel.writeByte(if (isAvailable) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OfferFirebaseModel> {
        override fun createFromParcel(parcel: Parcel): OfferFirebaseModel {
            return OfferFirebaseModel(parcel)
        }

        override fun newArray(size: Int): Array<OfferFirebaseModel?> {
            return arrayOfNulls(size)
        }
    }
}