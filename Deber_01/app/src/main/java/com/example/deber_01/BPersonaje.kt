package com.example.deber_01

import android.os.Parcel
import android.os.Parcelable

class BPersonaje(
    var idPersonaje: Int,
    var idAnime: Int,
    var nombrePersonaje: String,
    var edadPersonaje: Int,
    var generoPersonaje: String,
):Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt()!!,
        parcel.readString()!!
    ){

    }

    override fun toString(): String {
        return "${nombrePersonaje} - ${edadPersonaje} - ${generoPersonaje}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idPersonaje)
        parcel.writeInt(idAnime)
        parcel.writeString(nombrePersonaje)
        parcel.writeInt(edadPersonaje)
        parcel.writeString(generoPersonaje)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BPersonaje> {
        override fun createFromParcel(parcel: Parcel): BPersonaje {
            return BPersonaje(parcel)
        }

        override fun newArray(size: Int): Array<BPersonaje?> {
            return arrayOfNulls(size)
        }
    }
}