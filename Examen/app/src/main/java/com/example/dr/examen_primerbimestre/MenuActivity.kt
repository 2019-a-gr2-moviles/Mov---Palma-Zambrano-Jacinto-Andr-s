package com.example.mjg70.examen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    var usuario :String = txtNombreAutor.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        usuario = intent.getStringExtra("usuario").toString()

        btnAutor.setOnClickListener {gestionarPadre() }
        btnCrearAutor.setOnClickListener{ crearEquipo()}
    }

    fun gestionarPadre(){
        val intentGestionarPadre = Intent(this, ConsultarAutorActivity::class.java)
        intentGestionarPadre.putExtra("usuario", usuario)
        startActivity(intentGestionarPadre)
    }

    fun crearEquipo(){
        val intentCrearEquipo = Intent(this, IngresarAutorActivity::class.java)
        intentCrearEquipo.putExtra("usuario", usuario)
        startActivity(intentCrearEquipo)
    }

    override fun onBackPressed() {
        val intentMenu = Intent(this, MainActivity::class.java)

        startActivity(intentMenu)
    }
}
