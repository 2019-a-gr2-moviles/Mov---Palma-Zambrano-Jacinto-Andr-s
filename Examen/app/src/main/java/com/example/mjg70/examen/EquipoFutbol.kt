package com.example.mjg70.examen

import android.os.Parcel
import android.os.Parcelable


class EquipoFutbol(var id:Int?,
              var nombre:String,
              var liga:String,
              var fechaCreacion:String,
              var numeroCopasInternacionales:Int, var campeonActual:String) :Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "NOMBRE: ${nombre} LIGA: ${liga} FECHA CREACION: ${fechaCreacion} NUMERO COPAS:${numeroCopasInternacionales} CAMPEON ACTUAL:${campeonActual}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nombre)
        parcel.writeString(liga)
        parcel.writeString(fechaCreacion)
        parcel.writeInt(numeroCopasInternacionales)
        parcel.writeString(campeonActual)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EquipoFutbol> {
        override fun createFromParcel(parcel: Parcel): EquipoFutbol {
            return EquipoFutbol(parcel)
        }

        override fun newArray(size: Int): Array<EquipoFutbol?> {
            return arrayOfNulls(size)
        }
    }
}