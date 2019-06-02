package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnEntrar.setOnClickListener { ingresarUsuario() }
    }

    fun ingresarUsuario(){
        BDEquipoFutbol.guardarUsuario(txtNombre.text.toString())
        val intentMenu = Intent(this, MenuActivity::class.java)
        intentMenu.putExtra("usuario", txtNombre.text.toString())
        startActivity(intentMenu)
    }
}
