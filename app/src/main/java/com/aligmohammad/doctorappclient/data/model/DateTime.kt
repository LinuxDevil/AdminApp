

package com.aligmohammad.doctorappclient.data.model

import android.os.Parcel
import android.os.Parcelable

data class DateTime(
    var text: String?,
    var selected: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeByte(if (selected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DateTime> {
        override fun createFromParcel(parcel: Parcel): DateTime {
            return DateTime(parcel)
        }

        override fun newArray(size: Int): Array<DateTime?> {
            return arrayOfNulls(size)
        }
    }
}
