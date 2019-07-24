package com.example.parcelable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fragmentos.*

class FragmentosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmentos)

        btnPrimero.setOnClickListener{
            abrirPrimerFragmento()
        }
        btnSegundo.setOnClickListener{
            abrirSegundoFragmento()
        }
    }



    private fun abrirPrimerFragmento() {
       //Llamar a un manager
        val fragmentManager = supportFragmentManager
       // Empezar la transacción
        val transaccion = fragmentManager.beginTransaction()
        //  Definir la instancia del fragmento
        val primerFragment = PrimerFragment()
        // Reemplazamos el fragmento
        transaccion.replace(R.id.cly_fragmentos,primerFragment)
        // Terminar la transaccion
        transaccion.commit()
    }

    private fun abrirSegundoFragmento() {
        //Llamar a un manager
        val fragmentManager = supportFragmentManager
        // Empezar la transacción
        val transaccion = fragmentManager.beginTransaction()
        //  Definir la instancia del fragmento
        val segundoFragment = SegundoFragment()
        // Reemplazamos el fragmento
        transaccion.replace(R.id.cly_fragmentos,segundoFragment)
        // Terminar la transaccion
        transaccion.commit()
    }


}
