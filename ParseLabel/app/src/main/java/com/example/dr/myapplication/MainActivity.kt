package com.example.dr.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun irAParcelable(){
        val intentExplicito = Intent( this,
            Parcelabel::class.java)
        val jacinto = Usuario( "Jacinto", 25, Date(),33.5)
        intentExplicito.putExtra("usuario",jacinto)
        startActivity(intentExplicito)

        val cachetes = Mascota("cachetes",jacinto)
        intentExplicito.putExtra("mascota",cachetes)
    }
}
