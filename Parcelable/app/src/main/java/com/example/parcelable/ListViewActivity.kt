package com.example.parcelable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.support.design.widget.Snackbar
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listaNombres= arrayListOf<String>()
        listaNombres.add("Jorge")
        listaNombres.add("Jacinto")
        listaNombres.add("Renny")
        listaNombres.add("Mario")
        listaNombres.add("Alejandro")
        listaNombres.add("Diego")

        val adapter= ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,listaNombres)
        lv_ejemplo.adapter=adapter
        lv_ejemplo.onItemClickListener= AdapterView.OnItemClickListener{parent, view, position, id ->


            Log.i("list-view","Posicion $position")

            Snackbar
                .make(view, "Gracias por crear un nuevo usuario", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()



        }
    }
}
