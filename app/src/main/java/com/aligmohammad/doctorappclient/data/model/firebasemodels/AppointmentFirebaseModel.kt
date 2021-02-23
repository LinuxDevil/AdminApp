package com.aligmohammad.doctorappclient.data.model.firebasemodels

import android.os.Parcel
import android.os.Parcelable

data class AppointmentFirebaseModel(
    var date: String?,
    var time: String?,
    var shift: String?,
    var userId: String?,
    var doctorId: String?,
    var placeId: String?,
    var tests: String?,
    var inProgress: Boolean,
    var uuid: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
    ) {
    }

    constructor() : this("", "", "", "", "", "", "", true, "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeString(shift)
        parcel.writeString(userId)
        parcel.writeString(doctorId)
        parcel.writeString(placeId)
        parcel.writeString(tests)
        parcel.writeByte(if (inProgress) 1 else 0)
        parcel.writeString(uuid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppointmentFirebaseModel> {
        override fun createFromParcel(parcel: Parcel): AppointmentFirebaseModel {
            return AppointmentFirebaseModel(parcel)
        }

        override fun newArray(size: Int): Array<AppointmentFirebaseModel?> {
            return arrayOfNulls(size)
        }
    }
}