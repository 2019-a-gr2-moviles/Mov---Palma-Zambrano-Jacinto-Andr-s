package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar.*

class ActualizarAutorActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar)
        usuario = intent.getStringExtra("usuario").toString()
        val equipoRecibida = intent.getParcelableExtra<Autor>("Equipo")
        txtNombre.setText(equipoRecibida.nombres.toString())
        txtLiga.setText(equipoRecibida.apellidos.toString())
        txtFechaCreacion.setText(equipoRecibida.fechaNacimiento.toString())
        txtNumCopInter.setText(equipoRecibida.numeroLibros.toString())
        txtCampeonAct.setText(equipoRecibida.ecuatoriano.toString())
        padreId = equipoRecibida.id!!;
        btnActualizar.setOnClickListener { actualizarEquipo() }
        btnEliminar.setOnClickListener { eliminarEquipo() }
        btnCrearJugador.setOnClickListener { crearJugador() }
        btnGestionarJugador.setOnClickListener { gestionarJugador() }
        btnMenuRetorno.setOnClickListener { retorno() }
    }

    fun actualizarEquipo(){
        val actualizarEquipo = Autor(id = padreId,
            nombres = txtNombre.text.toString(),
            apellidos = txtLiga.text.toString(),
            fechaNacimiento = txtFechaCreacion.text.toString(),
            numeroLibros = txtNumCopInter.text.toString().toInt(),
            ecuatoriano = txtCampeonAct.text.toString()
        )
        BDAutores.actualizarEquipo(actualizarEquipo)
        Toast.makeText(this, "Actualización exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun eliminarEquipo(){
        BDAutores.eliminarEquipo(padreId);
        Toast.makeText(this, "Eliminación exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun crearJugador(){
        val equipoRespaldo = Autor(id = padreId,
            nombres = txtNombre.text.toString(),
            apellidos = txtLiga.text.toString(),
            fechaNacimiento = txtFechaCreacion.text.toString(),
            numeroLibros = txtNumCopInter.text.toString().toInt(),
            ecuatoriano = txtCampeonAct.text.toString()
        )
        val retorno = Intent(this, IngresarLibroActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("padreId", padreId)
        retorno.putExtra("EquipoRespaldo", equipoRespaldo)
        startActivity(retorno)
    }

    fun gestionarJugador(){
        val equipoRespaldo = Autor(id = padreId,
            nombres = txtNombre.text.toString(),
            apellidos = txtLiga.text.toString(),
            fechaNacimiento = txtFechaCreacion.text.toString(),
            numeroLibros = txtNumCopInter.text.toString().toInt(),
            ecuatoriano = txtCampeonAct.text.toString()
        )
        val retorno = Intent(this, ConsultarLibroActivity::class.java)
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
