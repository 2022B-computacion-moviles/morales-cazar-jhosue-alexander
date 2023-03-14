package com.example.examen02

import android.os.Parcel
import android.os.Parcelable

class Anime(
    var idAnime: String,
    var nombreAnime: String,
    var anoDeLanzamientoAnime: String,
    var generoAnime: String,
    var estudioAnime: String,
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun toString(): String {
        return "${nombreAnime} - ${anoDeLanzamientoAnime} - ${generoAnime} - ${estudioAnime}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idAnime)
        parcel.writeString(nombreAnime)
        parcel.writeString(anoDeLanzamientoAnime)
        parcel.writeString(generoAnime)
        parcel.writeString(estudioAnime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Anime> {
        override fun createFromParcel(parcel: Parcel): Anime {
            return Anime(parcel)
        }

        override fun newArray(size: Int): Array<Anime?> {
            return arrayOfNulls(size)
        }
    }

    fun getID(): String{
        return idAnime
    }

}
