package com.example.mjg70.examen

import android.os.Parcel
import android.os.Parcelable

class Libro(var id:Int?,
            var ICBN:Int,
            var nombreLibro:String,
            var numeroPaginas:Int,
            var editorial:Int,
            var fechaNacimiento:String,
            var numEdicion:Int,
            var autorId:Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return "Número Camiseta: ${ICBN} Nombre Libro: ${numeroPaginas} Poder: ${editorial} Fecha Ingreso:${fechaNacimiento} Goles:${numEdicion}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(ICBN)
        parcel.writeString(nombreLibro)
        parcel.writeInt(numeroPaginas)
        parcel.writeInt(editorial)
        parcel.writeString(fechaNacimiento)
        parcel.writeInt(numEdicion)
        parcel.writeInt(autorId)
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
