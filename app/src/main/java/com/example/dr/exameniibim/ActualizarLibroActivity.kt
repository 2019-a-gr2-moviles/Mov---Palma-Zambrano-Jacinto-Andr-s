package com.example.dr.exameniibim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_actualizar_libro.*

class ActualizarLibroActivity : AppCompatActivity() {

    var usuario: String = ""
    var id: String = ""
    var idLibro:String =""
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_libro)

        usuario = intent.getStringExtra("usuario").toString()
        id = intent.getStringExtra("id").toString()
        idLibro = intent.getStringExtra("idLibro").toString()

        ref = FirebaseDatabase.getInstance().getReference("Libros").child(id)

        //Agrega los valores del objeto en los campos
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {

                    for (libro in p0.children) {
                        val lib = libro.getValue(Libro::class.java)
                        if(lib!!.idLibro == idLibro){
                            txtICBN.setText(lib.icbn)
                            txtEditorial.setText(lib.editorial)
                            txtnumEdicion.setText(lib.numEdicion)
                            txtfechaPublicacion.setText(lib.fechaPublicacion)
                            txtNombreLibro.setText(lib.nombreLibro)
                            txtNumPaginas.setText(lib.numeroPaginas)
                            //txtLatitud.setText(lib.latitud.toString())
                            //txtLongitud.setText(lib.longitud.toString())

                        }
                    }
                }
            }
        })



    }
}
