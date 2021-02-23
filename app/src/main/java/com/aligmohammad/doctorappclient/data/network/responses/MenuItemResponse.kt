package com.aligmohammad.doctorappclient.data.network.responses

import android.os.Parcel
import android.os.Parcelable

class MenuItemResponse : ArrayList<MenuItemResponseItem>()

data class MenuItemResponseItem(
    val id: Int,
    val image_url: String?,
    val menu_id: String?,
    val name_ar: String?,
    val name_en: String?,
    val published_at: String?,
    val updated_at: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(image_url)
        parcel.writeString(menu_id)
        parcel.writeString(name_ar)
        parcel.writeString(name_en)
        parcel.writeString(published_at)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuItemResponseItem> {
        override fun createFromParcel(parcel: Parcel): MenuItemResponseItem {
            return MenuItemResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<MenuItemResponseItem?> {
            return arrayOfNulls(size)
        }
    }

}