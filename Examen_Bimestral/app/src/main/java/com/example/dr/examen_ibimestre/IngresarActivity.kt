package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ingresar_autor.*

class IngresarActivity : AppCompatActivity() {
    var usuario:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_autor)
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
        val autor= Autor(id = null,
            nombres = txtNombresAutor.text.toString(),
            apellidos = txtApellidosAutor.text.toString(),
            fechaNacimiento = txtFechaNacimiento.text.toString(),
            numeroLibros = txtNumLibrosAutor.text.toString().toInt(),
            campeonActual = txtCampeonActual.text.toString())
        BDAutores.agregarEquipo(autor)
        Toast.makeText(this, "Ingreso exitoso "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }
}
