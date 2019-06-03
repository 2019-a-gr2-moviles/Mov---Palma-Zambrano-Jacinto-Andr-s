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
    var equipoRespaldo : Autor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_libro)
        usuario = intent.getStringExtra("usuario").toString()
        val jugadorRecibido = intent.getParcelableExtra<Libro>("Libro")
        equipoRespaldo = intent.getParcelableExtra<Autor>("EquipoRespaldo")
        txtICBN.setText(jugadorRecibido.numeroCamiseta.toString())
        txtNombreLibro.setText(jugadorRecibido.nombreCamiseta.toString())
        txtEditorial.setText(jugadorRecibido.nombreCompletoJugador.toString())
        txtIdAutor.setText(jugadorRecibido.poderEspecialDos.toString())
        txtfechaPublicacion.setText(jugadorRecibido.fechaIngresoEquipo.toString())
        txtNumPaginas.setText(jugadorRecibido.goles.toString())
        id = jugadorRecibido.id.toString().toInt()
        idPadre = jugadorRecibido.equipoFutbolId.toString().toInt()
        btnActualizarJugador.setOnClickListener { actualizarJugador() }
        btnEliminarJugador.setOnClickListener { eliminarJugador() }
    }

    fun actualizarJugador(){
        val jugador = Libro(id = id,
            numeroCamiseta = txtICBN.text.toString().toInt(),
            nombreCamiseta = txtNombreLibro.text.toString(),
            nombreCompletoJugador = txtEditorial.text.toString(),
            poderEspecialDos = txtIdAutor.text.toString(),
            fechaIngresoEquipo = txtfechaPublicacion.text.toString(),
            goles = txtNumPaginas.text.toString().toInt(),
            equipoFutbolId = idPadre)
        BDLibros.actualizarJugador(jugador)
        Toast.makeText(this, "Actualización jugador exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarAutorActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Equipo", equipoRespaldo)
        startActivity(retorno)
    }

    fun eliminarJugador(){
        BDLibros.eliminarJugador(id)
        Toast.makeText(this, "Eliminación jugador exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarAutorActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Equipo", equipoRespaldo)
        startActivity(retorno)
    }
}
