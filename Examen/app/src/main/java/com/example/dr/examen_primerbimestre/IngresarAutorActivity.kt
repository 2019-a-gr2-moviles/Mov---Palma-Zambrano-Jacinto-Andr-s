package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_ingresar_autor.*

class IngresarAutorActivity : AppCompatActivity() {
    var usuario:String = ""
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_autor)
        usuario = intent.getStringExtra("usuario").toString()


        btnAceptar.setOnClickListener { aceptarIngreso() }


        btnCancelar.setOnClickListener { cancelarIngreso()}
    }

    fun cancelarIngreso(){
        val intentCancelar= Intent(this, MenuActivity::class.java)
        intentCancelar.putExtra("usuario", usuario)
        startActivity(intentCancelar)
    }

    fun aceptarIngreso(){
        val id = null
        val   nombres = txtNombreAutor.text.toString().trim()
        val  apellidos = txtApellidosAutor.text.toString().trim()
        val  fechaNacimiento = txtFechaNacimientoAutor.text.toString().trim()
        val   numeroLibros = numLibros.text.toString().toInt()
        val   ecuatoriano = txtEcuatoriano.text.toString().trim()

        if(TextUtils.isEmpty(nombres) || TextUtils.isEmpty(apellidos) || TextUtils.isEmpty(fechaNacimiento) ){
            txtNombreAutor.error = "Ingrese el nombre del autor"
        }






       // BDAutores.agregarAutor(autorNuevo)
        Toast.makeText(this, "Ingreso exitoso "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }



    companion object {

        private const val TAG = "NewPostActivity"
        private const val REQUIRED = "Required"
    }

    override fun onBackPressed() {

        val intentMenu = Intent(this, MenuActivity::class.java)
        intentMenu.putExtra("usuario", usuario)
        startActivity(intentMenu)
    }
}
