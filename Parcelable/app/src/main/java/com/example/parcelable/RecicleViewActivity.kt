package com.example.parcelable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.AdapterViewAnimator
import kotlinx.android.synthetic.main.activity_recicle_view.*

class RecicleViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicle_view)
        val lista= arrayListOf<Persona>()
        val recyler_view= rv_personas
        val actividad=this
        lista.add(Persona("Jorge","1316509213"))
        lista.add(Persona("Mario","1304292483"))
        lista.add(Persona("Jacinto","2003938121"))

        val adaptadorPersona=AdaptadorPersona(lista, actividad, recyler_view)
        recyler_view.adapter=adaptadorPersona
        rv_personas.itemAnimator= androidx.recyclerview.widget.DefaultItemAnimator()
        rv_personas.layoutManager= androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptadorPersona.notifyDataSetChanged()




    }
    fun iniciarRecylerView(
        lista: List<Persona>,
        actividad: RecicleViewActivity,
        recycler_view: androidx.recyclerview.widget.RecyclerView
    ) {
        val adaptadorPersona = AdaptadorPersona(
            lista,
            actividad,
            recycler_view
        )
        recycler_view.adapter = adaptadorPersona
        recycler_view.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recycler_view.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)

        adaptadorPersona.notifyDataSetChanged()
    }

    fun cambiarNombreTextView(texto: String){
        txv_titulo_rv.text=texto
    }
}
