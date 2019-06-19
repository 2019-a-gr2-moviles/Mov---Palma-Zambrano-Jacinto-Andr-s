package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ingresar_libro.*

class IngresarLibroActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    var autorRespaldo : Autor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_libro)
        usuario = intent.getStringExtra("usuario").toString()
        autorRespaldo = intent.getParcelableExtra<Autor>("AutorRespaldo")
        padreId = intent.getIntExtra("padreId", -1)
        btnGuardar.setOnClickListener { guardarLibro() }
    }

    fun guardarLibro(){
        val libro = Libro(id = null,
            ICBN = txtICBN.text.toString().toInt(),
            nombreLibro = txtNombreLibro.text.toString(),
            numeroPaginas = txtNumPaginas.text.toString().toInt(),
            editorial = txtEditorial.text.toString(),
            fechaPublicacion = txtfechaPublicacion.text.toString(),
            numEdicion = txtnumEdicion.text.toString().toInt(),
            autorId = padreId
        )
        BDLibros.agregarLibro(libro)
        Toast.makeText(this, "Libro creado exitosamente "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarAutorActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Autor", autorRespaldo)
        startActivity(retorno)
    }

    override fun onBackPressed() {

        val intentMenu = Intent(this, ActualizarAutorActivity::class.java)
        intentMenu.putExtra("usuario", usuario)

        intentMenu.putExtra("Autor", autorRespaldo)

        startActivity(intentMenu)
    }
}
