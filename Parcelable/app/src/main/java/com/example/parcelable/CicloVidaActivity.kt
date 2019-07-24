package com.example.parcelable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_vida.*

class CicloVidaActivity : AppCompatActivity() {

    var contador=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        Log.i("ciclo-vida","onCreate")

        btn_contador.setOnClickListener{
            aumentarContador()
        }
    }

    private fun aumentarContador() {

        contador++
        txt_contador.text = contador.toString();
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida","onRestart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida","onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida","onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Log.i("ciclo-vida","onSaveInstanceState")
        super.onSaveInstanceState(outState)
        outState?.run{
            putInt("contadorGuardado",contador)
        }
    }



    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("ciclo-vida","onRestoreInstanceState")
        val contadorRecuperado = savedInstanceState?.getInt("contadorGuardado")
        if (contadorRecuperado!=null){
            this.contador=contadorRecuperado
            txt_contador.text = this.contador.toString()

        }
    }
}
