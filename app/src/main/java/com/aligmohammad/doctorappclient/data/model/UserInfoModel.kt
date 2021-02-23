package com.aligmohammad.doctorappclient.data.model

import android.os.Parcel
import android.os.Parcelable

data class UserInfoModelInsurance(
    val insuranceNumber: String?,
    val userId: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(insuranceNumber)
        parcel.writeString(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserInfoModelInsurance> {
        override fun createFromParcel(parcel: Parcel): UserInfoModelInsurance {
            return UserInfoModelInsurance(parcel)
        }

        override fun newArray(size: Int): Array<UserInfoModelInsurance?> {
            return arrayOfNulls(size)
        }
    }

}

data class UserInfoModelNationalId(
    val nid: String?,
    val userId: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nid)
        parcel.writeString(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserInfoModelInsurance> {
        override fun createFromParcel(parcel: Parcel): UserInfoModelInsurance {
            return UserInfoModelInsurance(parcel)
        }

        override fun newArray(size: Int): Array<UserInfoModelInsurance?> {
            return arrayOfNulls(size)
        }
    }

}

data class UserInfoModel (
    val name: String?,
    val image_url: String?,
    val nid: String?,
    val dob: String?,
    val userId: String?,
    val insuranceNumber: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {  }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(image_url)
        parcel.writeString(nid)
        parcel.writeString(dob)
        parcel.writeString(userId)
        parcel.writeString(insuranceNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserInfoModel> {
        override fun createFromParcel(parcel: Parcel): UserInfoModel {
            return UserInfoModel(parcel)
        }

        override fun newArray(size: Int): Array<UserInfoModel?> {
            return arrayOfNulls(size)
        }
    }

}
