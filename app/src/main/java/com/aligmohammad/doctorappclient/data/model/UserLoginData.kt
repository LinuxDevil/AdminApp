package com.aligmohammad.doctorappclient.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UserLoginData(
    @SerializedName("identifier")
    val username: String? = "",
    val password: String? = "",
    @SerializedName("phone")
    val phoneNumber: String? = "",
    val email: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(phoneNumber)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserLoginData> {
        override fun createFromParcel(parcel: Parcel): UserLoginData {
            return UserLoginData(parcel)
        }

        override fun newArray(size: Int): Array<UserLoginData?> {
            return arrayOfNulls(size)
        }
    }

}

data class RegisterLoginData(
    @SerializedName("username")
    val username: String? = "",
    val password: String? = "",
    @SerializedName("phone")
    val phoneNumber: String? = "",
    val email: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(phoneNumber)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserLoginData> {
        override fun createFromParcel(parcel: Parcel): UserLoginData {
            return UserLoginData(parcel)
        }

        override fun newArray(size: Int): Array<UserLoginData?> {
            return arrayOfNulls(size)
        }
    }

}