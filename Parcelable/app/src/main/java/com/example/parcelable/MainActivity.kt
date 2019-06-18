package com.example.parcelable

import android.content.Intent
import android.support.v7.app.AppCompatActivity
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
    }
    fun irARecyclerView(){
        val intentExplicito= Intent(this, RecicleViewActivity::class.java)
        startActivity(intentExplicito)
    }

    fun irARespuesta(){
        val intentExplicito= Intent(this, IntentRespuesta::class.java)
        startActivity(intentExplicito)
    }

}
