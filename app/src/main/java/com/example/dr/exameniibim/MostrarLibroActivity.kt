package com.example.dr.exameniibim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_actualizar_libro.*
import kotlinx.android.synthetic.main.activity_mostrar_libro.*

class MostrarLibroActivity : AppCompatActivity() {

    var idLibroRecibido:String =""
    lateinit var ref: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_libro)

        idLibroRecibido = intent.getStringExtra("idLibroRecibido").toString()

        ref = FirebaseDatabase.getInstance().getReference("Libros")
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
                                txtIcbnMostrar.setText(lib.icbn.toString())
                                txtEditorialMostrar.setText(lib.editorial.toString())
                                txtEdicionMostrar.setText(lib.numEdicion.toString())
                                txtFechaMostrar.setText(lib.fechaPublicacion.toString())
                                txtNombreLibroMostrar.setText(lib.nombreLibro.toString())
                                txtNumPagMostrar.setText(lib.numeroPaginas.toString())
                                txtLatitudMostrar.setText(lib.latitud.toString())
                                txtLongitudMostrar.setText(lib.longitud.toString())

                            }
                        }
                    }
                }
            }
        })
    }
}
