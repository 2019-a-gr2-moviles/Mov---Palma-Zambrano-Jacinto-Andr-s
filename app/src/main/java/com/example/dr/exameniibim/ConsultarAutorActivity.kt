package com.example.dr.exameniibim


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*

class ConsultarAutorActivity : AppCompatActivity() {

    var usuario:String =""
    lateinit var ref: DatabaseReference
    lateinit var listaAutores: MutableList<Autor>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_autor)

        listView = findViewById(R.id.lstView)
        listaAutores = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Autores")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    listaAutores.clear()
                    for(autor in p0.children){
                        val aut = autor.getValue(Autor::class.java)
                        listaAutores.add(aut!!)
                    }

                    val adapter = AdaptadorAutor(applicationContext,R.layout.autores,listaAutores)
                    listView.adapter = adapter
                    listView.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->

                        val idAutorSeleccionado = listaAutores[position].id
                        val intentAutorSeleccionado = Intent(this@ConsultarAutorActivity,ActualizarAutorActivity::class.java)
                        intentAutorSeleccionado.putExtra("id", idAutorSeleccionado)
                        intentAutorSeleccionado.putExtra("usuario", usuario)

                        startActivity(intentAutorSeleccionado)
                    }

                }
            }

        })


    }




}
