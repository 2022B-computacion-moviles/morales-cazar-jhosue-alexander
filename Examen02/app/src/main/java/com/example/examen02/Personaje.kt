package com.example.examen02

import android.os.Parcel
import android.os.Parcelable

class Personaje(
    var idPersonaje: String,
    var idAnime: String,
    var nombrePersonaje: String,
    var edadPersonaje: String,
    var generoPersonaje: String,
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ){

    }

    override fun toString(): String {
        return "${nombrePersonaje} - ${edadPersonaje} - ${generoPersonaje}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idPersonaje)
        parcel.writeString(idAnime)
        parcel.writeString(nombrePersonaje)
        parcel.writeString(edadPersonaje)
        parcel.writeString(generoPersonaje)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Personaje> {
        override fun createFromParcel(parcel: Parcel): Personaje {
            return Personaje(parcel)
        }

        override fun newArray(size: Int): Array<Personaje?> {
            return arrayOfNulls(size)
        }
    }
}