package com.example.proyectofinal

import android.os.Parcel
import android.os.Parcelable

class EjercicioLista(
    var idEjercicioLista: String,
    var nombreEjercicioLista: String,
    var enlaceEjercicioLista: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idEjercicioLista)
        parcel.writeString(nombreEjercicioLista)
        parcel.writeString(enlaceEjercicioLista)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EjercicioLista> {
        override fun createFromParcel(parcel: Parcel): EjercicioLista {
            return EjercicioLista(parcel)
        }

        override fun newArray(size: Int): Array<EjercicioLista?> {
            return arrayOfNulls(size)
        }
    }
}