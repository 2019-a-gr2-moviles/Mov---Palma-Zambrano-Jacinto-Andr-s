package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_consultar_autor.*

class ConsultarAutorActivity : AppCompatActivity() {
    var usuario :String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_autor)
        usuario = intent.getStringExtra("usuario").toString()
        val adapter = ArrayAdapter<Autor>(
            this,
            android.R.layout.simple_list_item_1,
            BDAutores.mostrarAutor()
        )
        lstView.adapter = adapter;
        lstView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val equipoSeleccionada = parent.getItemAtPosition(position) as Autor
            val intentEquipoSeleccionada = Intent(this, ActualizarAutorActivity::class.java)
            intentEquipoSeleccionada.putExtra("Autor", equipoSeleccionada)
            intentEquipoSeleccionada.putExtra("usuario", usuario)
            startActivity(intentEquipoSeleccionada)
        }




    }

    override fun onBackPressed() {

        val intentMenu = Intent(this, MenuActivity::class.java)
        intentMenu.putExtra("usuario", usuario)
        startActivity(intentMenu)
    }
}
