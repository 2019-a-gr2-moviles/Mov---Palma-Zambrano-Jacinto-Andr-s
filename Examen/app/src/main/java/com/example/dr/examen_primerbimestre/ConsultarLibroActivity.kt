package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_consultar_libro.*

class ConsultarLibroActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    var equipoRespaldo : Autor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usuario = intent.getStringExtra("usuario").toString()
        equipoRespaldo = intent.getParcelableExtra<Autor>("AutorRespaldo")
        padreId = intent.getIntExtra("padreId", -1)
        setContentView(R.layout.activity_consultar_libro)
        val adapter = ArrayAdapter<Libro>(
            this,
            android.R.layout.simple_list_item_1,
            BDLibros.mostrarJugador(padreId)
        )
        lstJugador.adapter = adapter;
        lstJugador.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val jugadorSeleccionado = parent.getItemAtPosition(position) as Libro
            val intentJugadorSeleccionado = Intent(this, ActualizarLibroActivity::class.java)
            intentJugadorSeleccionado.putExtra("usuario", usuario)
            intentJugadorSeleccionado.putExtra("Libro", jugadorSeleccionado)
            intentJugadorSeleccionado.putExtra("AutorRespaldo", equipoRespaldo)
            startActivity(intentJugadorSeleccionado)
        }
    }
}
