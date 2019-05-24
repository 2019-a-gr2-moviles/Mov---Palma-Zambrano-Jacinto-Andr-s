package com.example.dr.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Parcelabel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelabel)

        val jacinto: Usuario?=this.intent.getParcelableExtra<Usuario>("usuario")

        Log.i("parcelable","Nombre ${jacinto?.nombre}")
        Log.i("parcelable","Edad ${jacinto?.edad}")
        Log.i("parcelable","Fecha Nacimiento ${jacinto?.fechaNacimiento}")
        Log.i("parcelable","Sueldo ${jacinto?.sueldo}")
    }


}
