package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_libro.*

class ActualizarLibroActivity : AppCompatActivity() {
    var id :Int = 0;
    var idPadre :Int = 0
    var usuario :String = "";
    var autorRespaldo : Autor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_libro)
        usuario = intent.getStringExtra("usuario").toString()
        val libroRecibido = intent.getParcelableExtra<Libro>("Libro")
        autorRespaldo = intent.getParcelableExtra<Autor>("AutorRespaldo")
        txtICBN.setText(libroRecibido.ICBN.toString())
        txtNombreLibro.setText(libroRecibido.nombreLibro.toString())
        txtEditorial.setText(libroRecibido.numeroPaginas.toString())
        txtnumEdicion.setText(libroRecibido.editorial.toString())
        txtfechaPublicacion.setText(libroRecibido.fechaPublicacion.toString())
        txtNumPaginas.setText(libroRecibido.numEdicion.toString())
        id = libroRecibido.id.toString().toInt()
        idPadre = libroRecibido.autorId.toString().toInt()
        btnActualizarLibro.setOnClickListener { actualizarJugador() }
        btnEliminarLibro.setOnClickListener { eliminarJugador() }
    }

    fun actualizarJugador(){
        val libro = Libro(id = id,
            ICBN = txtICBN.text.toString().toInt(),
            nombreLibro = txtNombreLibro.text.toString(),
            numeroPaginas = txtEditorial.text.toString().toInt(),
            editorial = txtnumEdicion.text.toString(),
            fechaPublicacion = txtfechaPublicacion.text.toString(),
            numEdicion = txtNumPaginas.text.toString().toInt(),
            autorId = idPadre)
        BDLibros.actualizarJugador(libro)
        Toast.makeText(this, "Actualización libro exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarAutorActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Autor", autorRespaldo)
        startActivity(retorno)
    }

    fun eliminarJugador(){
        BDLibros.eliminarJugador(id)
        Toast.makeText(this, "Eliminación libro exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarAutorActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Autor", autorRespaldo)
        startActivity(retorno)
    }

    override fun onBackPressed() {


        val intentMenu = Intent(this, ConsultarLibroActivity::class.java)
        intentMenu.putExtra("usuario", usuario)
        intentMenu.putExtra("padreId", idPadre)
        intentMenu.putExtra("AutorRespaldo", autorRespaldo)
        startActivity(intentMenu)
    }
}
