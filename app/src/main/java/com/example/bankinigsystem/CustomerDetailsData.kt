package com.example.bankinigsystem

import android.os.Parcel
import android.os.Parcelable

class CustomerDetailsData(var id:Int, var name: String?, var email: String?,var current_balance:Double) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeDouble(current_balance)
    }

    companion object CREATOR : Parcelable.Creator<CustomerDetailsData> {
        override fun createFromParcel(parcel: Parcel): CustomerDetailsData {
            return CustomerDetailsData(parcel)
        }

        override fun newArray(size: Int): Array<CustomerDetailsData?> {
            return arrayOfNulls(size)
        }
    }


}