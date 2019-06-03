package com.example.mjg70.examen

import android.os.Parcel
import android.os.Parcelable

class Libro(var id:Int?,
            var numeroCamiseta:Int,
            var nombreCamiseta:String,
            var nombreCompletoJugador:String,
            var poderEspecialDos:String,
            var fechaIngresoEquipo:String,
            var goles:Int,
            var equipoFutbolId:Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return "Número Camiseta: ${numeroCamiseta} Nombre Libro: ${nombreCompletoJugador} Poder: ${poderEspecialDos} Fecha Ingreso:${fechaIngresoEquipo} Goles:${goles}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(numeroCamiseta)
        parcel.writeString(nombreCamiseta)
        parcel.writeString(nombreCompletoJugador)
        parcel.writeString(poderEspecialDos)
        parcel.writeString(fechaIngresoEquipo)
        parcel.writeInt(goles)
        parcel.writeInt(equipoFutbolId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Libro> {
        override fun createFromParcel(parcel: Parcel): Libro {
            return Libro(parcel)
        }

        override fun newArray(size: Int): Array<Libro?> {
            return arrayOfNulls(size)
        }
    }
}
