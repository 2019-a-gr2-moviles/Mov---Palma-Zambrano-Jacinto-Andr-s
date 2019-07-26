package com.example.dr.exameniibim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*

class ConsultarLibroActivity : AppCompatActivity() {


    var usuario:String =""
    var id:String =""
    lateinit var ref: DatabaseReference
    lateinit var listaLibros: MutableList<Libro>
    lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_libro)

        usuario = intent.getStringExtra("usuario").toString()
        id = intent.getStringExtra("id").toString()

        listView = findViewById(R.id.lstLibros)
        listaLibros = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Libros").child(id)
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                //Toast.makeText(this@ConsultarLibroActivity, "Id recibido: ${id}  ID Snapshot: ${p0.key}",Toast.LENGTH_SHORT).show()
                if(p0.exists()){
                    listaLibros.clear()
                    for(libro in p0.children){
                        val lib = libro.getValue(Libro::class.java)
                        listaLibros.add(lib!!)
                    }

                    val adapter = AdaptadorLibro(applicationContext,R.layout.libros,listaLibros)
                    listView.adapter = adapter
                    listView.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->

                        val idLibroSeleccionado = listaLibros[position].idLibro
                        val intentLibroSeleccionado = Intent(this@ConsultarLibroActivity,ActualizarLibroActivity::class.java)
                        intentLibroSeleccionado.putExtra("idLibroRecibido", idLibroSeleccionado)
                        intentLibroSeleccionado.putExtra("usuario", usuario)
                        intentLibroSeleccionado.putExtra("id",id)

                        startActivity(intentLibroSeleccionado)
                    }

                }
            }

        })




    }
}
