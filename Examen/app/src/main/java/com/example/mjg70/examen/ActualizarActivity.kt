package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar.*

class ActualizarActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar)
        usuario = intent.getStringExtra("usuario").toString()
        val equipoRecibida = intent.getParcelableExtra<EquipoFutbol>("Equipo")
        txtNombre.setText(equipoRecibida.nombre.toString())
        txtLiga.setText(equipoRecibida.liga.toString())
        txtFechaCreacion.setText(equipoRecibida.fechaCreacion.toString())
        txtNumCopInter.setText(equipoRecibida.numeroCopasInternacionales.toString())
        txtCampeonAct.setText(equipoRecibida.campeonActual.toString())
        padreId = equipoRecibida.id!!;
        btnActualizar.setOnClickListener { actualizarEquipo() }
        btnEliminar.setOnClickListener { eliminarEquipo() }
        btnCrearJugador.setOnClickListener { crearJugador() }
        btnGestionarJugador.setOnClickListener { gestionarJugador() }
        btnMenuRetorno.setOnClickListener { retorno() }
    }

    fun actualizarEquipo(){
        val actualizarEquipo = EquipoFutbol(id = padreId,
            nombre = txtNombre.text.toString(),
            liga = txtLiga.text.toString(),
            fechaCreacion = txtFechaCreacion.text.toString(),
            numeroCopasInternacionales = txtNumCopInter.text.toString().toInt(),
            campeonActual = txtCampeonAct.text.toString()
        )
        BDEquipoFutbol.actualizarEquipo(actualizarEquipo)
        Toast.makeText(this, "Actualización exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun eliminarEquipo(){
        BDEquipoFutbol.eliminarEquipo(padreId);
        Toast.makeText(this, "Eliminación exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun crearJugador(){
        val equipoRespaldo = EquipoFutbol(id = padreId,
            nombre = txtNombre.text.toString(),
            liga = txtLiga.text.toString(),
            fechaCreacion = txtFechaCreacion.text.toString(),
            numeroCopasInternacionales = txtNumCopInter.text.toString().toInt(),
            campeonActual = txtCampeonAct.text.toString()
        )
        val retorno = Intent(this, IngresarJugadorActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("padreId", padreId)
        retorno.putExtra("EquipoRespaldo", equipoRespaldo)
        startActivity(retorno)
    }

    fun gestionarJugador(){
        val equipoRespaldo = EquipoFutbol(id = padreId,
            nombre = txtNombre.text.toString(),
            liga = txtLiga.text.toString(),
            fechaCreacion = txtFechaCreacion.text.toString(),
            numeroCopasInternacionales = txtNumCopInter.text.toString().toInt(),
            campeonActual = txtCampeonAct.text.toString()
        )
        val retorno = Intent(this, ConsultarJugadorActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("padreId", padreId)
        retorno.putExtra("EquipoRespaldo", equipoRespaldo)
        startActivity(retorno)
    }

    fun retorno(){
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }
}
