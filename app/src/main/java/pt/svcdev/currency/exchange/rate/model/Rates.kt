package pt.svcdev.currency.exchange.rate.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Rates(
    @field:SerializedName("tp") val tp: Int,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("from") val from: Int,
    @field:SerializedName("currMnemFrom") val currMnemFrom: String?,
    @field:SerializedName("to") val to: Int,
    @field:SerializedName("currMnemTo") val currMnemTo: String?,
    @field:SerializedName("basic") val basic: String?,
    @field:SerializedName("buy") val buy: String?,
    @field:SerializedName("sale") val sale: String?,
    @field:SerializedName("deltaBuy") val deltaBuy: String?,
    @field:SerializedName("deltaSale") val deltaSale: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
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
        parcel.writeInt(tp)
        parcel.writeString(name)
        parcel.writeInt(from)
        parcel.writeString(currMnemFrom)
        parcel.writeInt(to)
        parcel.writeString(currMnemTo)
        parcel.writeString(basic)
        parcel.writeString(buy)
        parcel.writeString(sale)
        parcel.writeString(deltaBuy)
        parcel.writeString(deltaSale)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rates> {
        override fun createFromParcel(parcel: Parcel): Rates {
            return Rates(parcel)
        }

        override fun newArray(size: Int): Array<Rates?> {
            return arrayOfNulls(size)
        }
    }
}