package com.example.mjg70.examen

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var nombreUsuario:String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnEntrar.setOnClickListener {
            ingresarUsuario() }


    }

    fun ingresarUsuario(){
        nombreUsuario = txtNombreAutor.text.toString()
        if(TextUtils.isEmpty(nombreUsuario)){
            Toast.makeText(this,"Debe de ingresar un nombre de usuario",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Bienvenido/a: ${nombreUsuario}",Toast.LENGTH_LONG).show()
          //  BDAutores.guardarUsuario(txtNombreAutor.text.toString())
            val intentMenu = Intent(this, MenuActivity::class.java)
            intentMenu.putExtra("usuario", txtNombreAutor.text.toString())
            startActivity(intentMenu)
        }

    }

    override fun onBackPressed() {

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
