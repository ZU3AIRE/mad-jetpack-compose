package com.example.jetpack_compose

import android.os.Parcel
import android.os.Parcelable

data class Contact(val name: String, val imageId: Int, val number: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "XXXX YYY",
        parcel.readInt(),
        parcel.readString() ?: "02XX-XXXXXXX"
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(imageId)
        parcel.writeString(number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}

