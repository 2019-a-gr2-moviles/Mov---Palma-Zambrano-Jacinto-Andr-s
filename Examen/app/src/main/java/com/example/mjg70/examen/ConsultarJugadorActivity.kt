package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_consultar.*
import kotlinx.android.synthetic.main.activity_consultar_jugador.*

class ConsultarJugadorActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    var equipoRespaldo : EquipoFutbol? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usuario = intent.getStringExtra("usuario").toString()
        equipoRespaldo = intent.getParcelableExtra<EquipoFutbol>("EquipoRespaldo")
        padreId = intent.getIntExtra("padreId", -1)
        setContentView(R.layout.activity_consultar_jugador)
        val adapter = ArrayAdapter<Jugador>(
            this,
            android.R.layout.simple_list_item_1,
            BDJugador.mostrarJugador(padreId)
        )
        lstJugador.adapter = adapter;
        lstJugador.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val jugadorSeleccionado = parent.getItemAtPosition(position) as Jugador
            val intentJugadorSeleccionado = Intent(this, ActualizarJugadorActivity::class.java)
            intentJugadorSeleccionado.putExtra("usuario", usuario)
            intentJugadorSeleccionado.putExtra("Jugador", jugadorSeleccionado)
            intentJugadorSeleccionado.putExtra("EquipoRespaldo", equipoRespaldo)
            startActivity(intentJugadorSeleccionado)
        }
    }
}
