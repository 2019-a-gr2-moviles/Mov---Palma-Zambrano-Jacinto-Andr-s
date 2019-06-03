package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ingresar.*

class IngresarAutorActivity : AppCompatActivity() {
    var usuario:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar)
        usuario = intent.getStringExtra("usuario").toString()
        btnAceptar.setOnClickListener { aceptarIngreso() }
        btnCancelar.setOnClickListener { cancelarIngreso()}
    }

    fun cancelarIngreso(){
        val intentCancelar= Intent(this, MenuActivity::class.java)
        intentCancelar.putExtra("usuario", usuario)
        startActivity(intentCancelar)
    }

    fun aceptarIngreso(){
        val equipo= Autor(id = null,
            nombres = txtNombre.text.toString(),
            apellidos = txtLiga.text.toString(),
            fechaNacimiento = txtFechaCreacion.text.toString(),
            numeroLibros = txtNumCopInter.text.toString().toInt(),
            ecuatoriano = txtCampeonActual.text.toString())
        BDAutores.agregarEquipo(equipo)
        Toast.makeText(this, "Ingreso exitoso "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }
}
