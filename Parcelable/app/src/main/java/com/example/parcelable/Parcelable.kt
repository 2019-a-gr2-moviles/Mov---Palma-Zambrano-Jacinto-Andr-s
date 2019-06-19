package com.example.parcelable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.parcelable.R

class Parcelable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable)

        val jorge: Usuario?=this.intent.getParcelableExtra<Usuario>("usuario")

        Log.i("parcelable","Nombre ${jorge?.nombre}")
        Log.i("parcelable","Edad ${jorge?.edad}")
        Log.i("parcelable","Fecha Nacimiento ${jorge?.fechaNacimiento}")
        Log.i("parcelable","Sueldo ${jorge?.sueldo}")
    }


}