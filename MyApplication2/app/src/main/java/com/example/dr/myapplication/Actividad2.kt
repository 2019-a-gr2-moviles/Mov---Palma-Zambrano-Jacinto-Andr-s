package com.example.dr.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log

import kotlinx.android.synthetic.main.activity_actividad2.*
import kotlinx.android.synthetic.main.content_actividad2.*

class Actividad2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad2)
        setSupportActionBar(toolbar)

        val nombre:String? = intent.getStringExtra("nombre")
        val edad:Int? = intent.getIntExtra("edad",0)
        Log.i("intents","Nombre: $nombre")
        Log.i("intents","Edad: $edad")

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        btn_ActividadUno.setOnClickListener { irAActividadUno() }

    }

    fun irAActividadUno(){

        val intent= Intent(
            this,
            MainActivity::class.java
        )
        startActivity(intent)
    }
}
