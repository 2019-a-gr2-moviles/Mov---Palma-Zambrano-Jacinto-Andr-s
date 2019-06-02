package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_jugador.*

class ActualizarJugadorActivity : AppCompatActivity() {
    var id :Int = 0;
    var idPadre :Int = 0
    var usuario :String = "";
    var equipoRespaldo : EquipoFutbol? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_jugador)
        usuario = intent.getStringExtra("usuario").toString()
        val jugadorRecibido = intent.getParcelableExtra<Jugador>("Jugador")
        equipoRespaldo = intent.getParcelableExtra<EquipoFutbol>("EquipoRespaldo")
        txtnumeroCamiseta.setText(jugadorRecibido.numeroCamiseta.toString())
        txtNombreCamiseta.setText(jugadorRecibido.nombreCamiseta.toString())
        txtNombreJugador.setText(jugadorRecibido.nombreCompletoJugador.toString())
        txtpoderEspecialDos.setText(jugadorRecibido.poderEspecialDos.toString())
        txtfechaIngresoEquipo.setText(jugadorRecibido.fechaIngresoEquipo.toString())
        txtGoles.setText(jugadorRecibido.goles.toString())
        id = jugadorRecibido.id.toString().toInt()
        idPadre = jugadorRecibido.equipoFutbolId.toString().toInt()
        btnActualizarJugador.setOnClickListener { actualizarJugador() }
        btnEliminarJugador.setOnClickListener { eliminarJugador() }
    }

    fun actualizarJugador(){
        val jugador = Jugador(id = id,
            numeroCamiseta = txtnumeroCamiseta.text.toString().toInt(),
            nombreCamiseta = txtNombreCamiseta.text.toString(),
            nombreCompletoJugador = txtNombreJugador.text.toString(),
            poderEspecialDos = txtpoderEspecialDos.text.toString(),
            fechaIngresoEquipo = txtfechaIngresoEquipo.text.toString(),
            goles = txtGoles.text.toString().toInt(),
            equipoFutbolId = idPadre)
        BDJugador.actualizarJugador(jugador)
        Toast.makeText(this, "Actualización jugador exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Equipo", equipoRespaldo)
        startActivity(retorno)
    }

    fun eliminarJugador(){
        BDJugador.eliminarJugador(id)
        Toast.makeText(this, "Eliminación jugador exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ActualizarActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("Equipo", equipoRespaldo)
        startActivity(retorno)
    }
}
