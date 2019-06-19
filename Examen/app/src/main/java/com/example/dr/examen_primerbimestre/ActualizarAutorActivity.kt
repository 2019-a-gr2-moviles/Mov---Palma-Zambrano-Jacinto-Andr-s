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
        val autorRecibido = intent.getParcelableExtra<Autor>("Autor")
        txtNombres.setText(autorRecibido.nombres)
        txtApellidos.setText(autorRecibido.apellidos.toString())
        txtFechaNacimiento.setText(autorRecibido.fechaNacimiento.toString())
        numLibros.setText(autorRecibido.numeroLibros.toString())
        txtCampeonAct.setText(autorRecibido.ecuatoriano.toString())
        padreId = autorRecibido.id!!;
        btnActualizar.setOnClickListener { actualizarAutor() }
        btnEliminar.setOnClickListener { eliminarAutor() }
        btnCrearLibro.setOnClickListener { crearLibro() }
        btnGestionarLIbros.setOnClickListener { gestionarJugador() }
        btnMenuRetorno.setOnClickListener { retorno() }
    }

    fun actualizarAutor(){
        val actualizarAutor = Autor(id = padreId,
            nombres = txtNombres.text.toString(),
            apellidos = txtApellidos.text.toString(),
            fechaNacimiento = txtFechaNacimiento.text.toString(),
            numeroLibros = numLibros.text.toString().toInt(),
            ecuatoriano = txtCampeonAct.text.toString()
        )
        BDAutores.actualizarAutor(actualizarAutor)
        Toast.makeText(this, "Actualización exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun eliminarAutor(){
        BDAutores.eliminarAutor(padreId);
        Toast.makeText(this, "Eliminación exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun crearLibro(){
        val autorRespaldo = Autor(id = padreId,
            nombres = txtNombres.text.toString(),
            apellidos = txtApellidos.text.toString(),
            fechaNacimiento = txtFechaNacimiento.text.toString(),
            numeroLibros = numLibros.text.toString().toInt(),
            ecuatoriano = txtCampeonAct.text.toString()
        )
        val retorno = Intent(this, IngresarLibroActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("padreId", padreId)
        retorno.putExtra("AutorRespaldo", autorRespaldo)
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
        retorno.putExtra("AutorRespaldo", equipoRespaldo)
        startActivity(retorno)
    }

    fun retorno(){
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    override fun onBackPressed() {

        val intentMenu = Intent(this, ConsultarAutorActivity::class.java)
        intentMenu.putExtra("usuario", usuario)
        startActivity(intentMenu)
    }
}
