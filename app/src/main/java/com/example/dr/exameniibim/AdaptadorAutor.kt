package com.example.dr.exameniibim

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AdaptadorAutor(val mCtx: Context, val layoutActual: Int, val listaAutores: List<Autor>):
 ArrayAdapter<Autor>(mCtx,layoutActual,listaAutores){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutActual,null)

        val textView = view.findViewById<TextView>(R.id.txtDatosAutor)

        val autorx = listaAutores[position]
        val stringAutor = "Nombres: ${autorx.nombres}  " + "Apellidos: ${autorx.apellidos}  " +
                "Fecha de Nacimiento: ${autorx.fechaNacimiento}  " + "Numero de Libros: ${autorx.numeroLibros}  " +
                "Ecuatoriano: ${autorx.ecuatoriano}"
        textView.text = stringAutor
        return view

    }
}