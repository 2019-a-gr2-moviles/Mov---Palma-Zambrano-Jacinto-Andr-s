package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_autor.*

class ActualizarAutorActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_autor)
        usuario = intent.getStringExtra("usuario").toString()
        val equipoRecibida = intent.getParcelableExtra<Autor>("Equipo")
        txtNombres.setText(equipoRecibida.nombres.toString())
        txtApellidos.setText(equipoRecibida.apellidos.toString())
        txtFechaNacimiento.setText(equipoRecibida.fechaNacimiento.toString())
        numLibros.setText(equipoRecibida.numeroLibros.toString())
        txtCampeonAct.setText(equipoRecibida.ecuatoriano.toString())
        padreId = equipoRecibida.id!!;
        btnActualizar.setOnClickListener { actualizarEquipo() }
        btnEliminar.setOnClickListener { eliminarEquipo() }
        btnCrearLibro.setOnClickListener { crearJugador() }
        btnGestionarLIbros.setOnClickListener { gestionarJugador() }
        btnMenuRetorno.setOnClickListener { retorno() }
    }

    fun actualizarEquipo(){
        val actualizarEquipo = Autor(id = padreId,
            nombres = txtNombres.text.toString(),
            apellidos = txtApellidos.text.toString(),
            fechaNacimiento = txtFechaNacimiento.text.toString(),
            numeroLibros = numLibros.text.toString().toInt(),
            ecuatoriano = txtCampeonAct.text.toString()
        )
        BDAutores.actualizarAutor(actualizarEquipo)
        Toast.makeText(this, "Actualización exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun eliminarEquipo(){
        BDAutores.eliminarAutor(padreId);
        Toast.makeText(this, "Eliminación exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun crearJugador(){
        val equipoRespaldo = Autor(id = padreId,
            nombres = txtNombres.text.toString(),
            apellidos = txtApellidos.text.toString(),
            fechaNacimiento = txtFechaNacimiento.text.toString(),
            numeroLibros = numLibros.text.toString().toInt(),
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
            nombres = txtNombres.text.toString(),
            apellidos = txtApellidos.text.toString(),
            fechaNacimiento = txtFechaNacimiento.text.toString(),
            numeroLibros = numLibros.text.toString().toInt(),
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
