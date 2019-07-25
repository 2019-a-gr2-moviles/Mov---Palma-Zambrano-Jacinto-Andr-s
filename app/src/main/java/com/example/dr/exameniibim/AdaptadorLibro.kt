package com.example.dr.exameniibim

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AdaptadorLibro(val mCtx: Context, val layoutActual: Int, val listaLibros: List<Libro>):
    ArrayAdapter<Libro>(mCtx,layoutActual,listaLibros){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutActual,null)

        val textView = view.findViewById<TextView>(R.id.txtDatosLibros)

        val librox = listaLibros[position]
        val stringLibro = "ICBN: ${librox.icbn}  " + "Editorial: ${librox.editorial}  " +
                "Fecha de Publicación: ${librox.fechaPublicacion}  " + "Nombre Libro: ${librox.nombreLibro}  " +
                "Numero de Edición: ${librox.numEdicion}" + "Numero de Páginas: ${librox.numeroPaginas}" + "Latitud: ${librox.latitud}" +
                "Longitud: ${librox.longitud}"
        textView.text = stringLibro
        return view

    }
}
