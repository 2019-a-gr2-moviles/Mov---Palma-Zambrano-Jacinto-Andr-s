package com.example.mjg70.examen

import android.os.Parcel
import android.os.Parcelable


class Autor(var id:Int?,
            var nombres:String,
            var apellidos:String,
            var fechaNacimiento:String,
            var numeroLibros:Int,
            var ecuatoriano:String) :Parcelable{

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
        return "NOMBRES: ${nombres} APELLIDOS: ${apellidos} FECHA NACIMIENTO: ${fechaNacimiento} " +
                "NUMERO LIBROS:${numeroLibros} ECUATORIANO:${ecuatoriano}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nombres)
        parcel.writeString(apellidos)
        parcel.writeString(fechaNacimiento)
        parcel.writeInt(numeroLibros)
        parcel.writeString(ecuatoriano)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Autor> {
        override fun createFromParcel(parcel: Parcel): Autor {
            return Autor(parcel)
        }

        override fun newArray(size: Int): Array<Autor?> {
            return arrayOfNulls(size)
        }
    }
}