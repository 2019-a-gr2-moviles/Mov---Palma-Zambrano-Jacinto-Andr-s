package com.example.dr.myapplication

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class AdaptadorPersona(private val listaPersonas: List<Persona>,
                        private val contexto: RecicleViewActivity,
                        private val recyclerView: RecyclerView
) :
    RecyclerView.Adapter<AdaptadorPersona.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: AdaptadorPersona.MyViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int):
            AdaptadorPersona.MyViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

