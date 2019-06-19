package com.example.parcelable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
        rv_personas.itemAnimator=DefaultItemAnimator()
        rv_personas.layoutManager=LinearLayoutManager(this)
        adaptadorPersona.notifyDataSetChanged()




    }
    fun iniciarRecylerView(
        lista: List<Persona>,
        actividad: RecicleViewActivity,
        recycler_view: RecyclerView
    ) {
        val adaptadorPersona = AdaptadorPersona(
            lista,
            actividad,
            recycler_view
        )
        recycler_view.adapter = adaptadorPersona
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.layoutManager = LinearLayoutManager(actividad)

        adaptadorPersona.notifyDataSetChanged()
    }

    fun cambiarNombreTextView(texto: String){
        txv_titulo_rv.text=texto
    }
}
