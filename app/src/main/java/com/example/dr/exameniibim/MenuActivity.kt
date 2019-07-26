package com.example.dr.exameniibim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
   var usuario:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        usuario = intent.getStringExtra("usuario").toString()


        btnCrearAutor.setOnClickListener {
            crearAutor()
        }

        btnAutor.setOnClickListener {
            mostrarAutores()
        }

        btnMapaHijos.setOnClickListener {
            accederMapa()
        }
    }

    private fun accederMapa() {
        val intentIrMapa = Intent(this, MapsActivity::class.java)
        intentIrMapa.putExtra("usuario", usuario)
        startActivity(intentIrMapa)
    }

    fun mostrarAutores() {
         val intentCrearAutor = Intent(this, ConsultarAutorActivity::class.java)
         intentCrearAutor.putExtra("usuario", usuario)
         startActivity(intentCrearAutor)
    }

    fun crearAutor() {
        val intentCrearAutor = Intent(this, IngresarAutorActivity::class.java)
        intentCrearAutor.putExtra("usuario", usuario)
        startActivity(intentCrearAutor)
    }

}
