package com.example.parcelable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_rv.setOnClickListener{
            irARecyclerView()
        }
        btnRespuesta.setOnClickListener(){
            irARespuesta()
        }
        btnHttp.setOnClickListener(){
            irAHTTP()
        }
        btnMap.setOnClickListener {
            irAMapa()
        }
        btn_CicloVida.setOnClickListener{
            irACicloDeVida()
        }
        btnFragmentos.setOnClickListener{
            irAFragmentos()
        }
    }

    private fun irAFragmentos() {
        val intentExplicito= Intent(this, FragmentosActivity::class.java)
        startActivity(intentExplicito)
    }

    private fun irACicloDeVida() {
        val intentExplicito= Intent(this, CicloVidaActivity::class.java)
        startActivity(intentExplicito)
    }

    fun irARecyclerView(){
        val intentExplicito= Intent(this, RecicleViewActivity::class.java)
        startActivity(intentExplicito)
    }
    fun irAHTTP(){
        val intentExplicito= Intent(this, ConexionHttpActivity::class.java)
        startActivity(intentExplicito)
    }


    fun irARespuesta(){
        val intentExplicito= Intent(this, IntentRespuesta::class.java)
        startActivity(intentExplicito)
    }

    fun irAMapa(){
        val intentExplicito= Intent(this, MapsActivity::class.java)
        startActivity(intentExplicito)
    }

}
