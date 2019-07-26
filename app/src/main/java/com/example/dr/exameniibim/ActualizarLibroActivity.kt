package com.example.dr.exameniibim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_actualizar_libro.*

class ActualizarLibroActivity : AppCompatActivity() {

    var usuario: String = ""
    var id: String = ""
    var idLibroRecibido:String =""
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_libro)

        usuario = intent.getStringExtra("usuario").toString()
        id = intent.getStringExtra("id").toString()
        idLibroRecibido = intent.getStringExtra("idLibroRecibido").toString()

        ref = FirebaseDatabase.getInstance().getReference("Libros").child(id)
        Log.i("idLIBRO",idLibroRecibido)

        //Agrega los valores del objeto en los campos
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {

                    for (libro in p0.children) {
                        var lib = libro.getValue(Libro::class.java)

                        if(lib!=null) {
                            if (TextUtils.equals(lib.idLibro, idLibroRecibido)) {
                                txtICBNA.setText(lib.icbn.toString())
                                txtEditorialA.setText(lib.editorial.toString())
                                txtnumEdicionA.setText(lib.numEdicion.toString())
                                txtfechaPublicacionA.setText(lib.fechaPublicacion.toString())
                                txtNombreLibroA.setText(lib.nombreLibro.toString())
                                txtNumPaginasA.setText(lib.numeroPaginas.toString())
                                txtLatitudA.setText(lib.latitud.toString())
                                txtLongitudA.setText(lib.longitud.toString())

                            }
                        }
                    }
                }
            }
        })


        btnActualizarLibro.setOnClickListener{
            actualizarLibro()
        }

        btnEliminarLibro.setOnClickListener {
            eliminarLibro()
        }



    }

    private fun eliminarLibro() {

        val drLibro : DatabaseReference = FirebaseDatabase.getInstance().getReference("Libros").child(id).child(idLibroRecibido)

        drLibro.removeValue()
        Toast.makeText(this,"${usuario} ha eliminado un Libro y su contenido",Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ConsultarLibroActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("idLibroRecibido", idLibroRecibido)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("id",id)

        startActivity(retorno)

    }

    private fun actualizarLibro() {
        val   ICBN = txtICBNA.text.toString().toInt()
        val  nombreLibro = txtNombreLibroA.text.toString().trim()
        val  numPaginas = txtNumPaginasA.text.toString().toInt()
        val   fechaPublicacion = txtfechaPublicacionA.text.toString()
        val   editorial = txtEditorialA.text.toString().trim()
        val edicion = txtnumEdicionA.text.toString().toInt()
        val latitud = txtLongitudA.text.toString().toFloat()
        val longitud = txtLongitudA.text.toString().toFloat()

        val Autorid = id
        val idLibrox = idLibroRecibido

        val referenceData = FirebaseDatabase.getInstance().getReference("Libros").child(Autorid)

        if(idLibrox!=null){
            val libroActualizado = Libro(idLibrox,ICBN,nombreLibro,numPaginas,editorial,fechaPublicacion,edicion,latitud,longitud)
            referenceData.child(idLibrox).setValue(libroActualizado).addOnCompleteListener{
                Toast.makeText(this, "Actualización exitosa "+usuario, Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Valor de ID nulo ", Toast.LENGTH_SHORT).show()
        }

        val retorno = Intent(this, ConsultarLibroActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }
}
