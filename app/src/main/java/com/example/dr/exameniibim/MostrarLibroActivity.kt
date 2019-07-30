package com.example.dr.exameniibim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_actualizar_libro.*
import kotlinx.android.synthetic.main.activity_mostrar_libro.*

class MostrarLibroActivity : AppCompatActivity() {


    lateinit var ref: DatabaseReference
    lateinit var ref2: DatabaseReference
    var arregloIdPadres = ArrayList<String?>()
    lateinit var datosHijo:Libro


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_libro)

        var idLibroRecibido = ServicioMapa.libroIntent

        Log.i("idMArker", idLibroRecibido)

        ref = FirebaseDatabase.getInstance().getReference("Libros")
        Log.i("idLIBRO", idLibroRecibido)

        ref = FirebaseDatabase.getInstance().getReference("Autores")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    for (autor in p0.children) {
                        arregloIdPadres.add("Libros/" + autor.key)
                    }
                    for (i in arregloIdPadres) {
                        Log.i("id del padre", i)
                        ref2 = FirebaseDatabase.getInstance().getReference(i!!)
                        ref2.addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(p1: DatabaseError) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onDataChange(p1: DataSnapshot) {
                                if (p1.exists()) {
                                    ServicioMapa.arregloIdHijos.clear()

                                    for (libro in p1.children) {
                                        ServicioMapa.arregloIdHijos.add(i + "/" + libro.key)
                                    }

                                    for (llave in ServicioMapa.arregloIdHijos) {
                                        Log.i("LlaveHijo", llave)
                                        ref2 = FirebaseDatabase.getInstance().getReference(llave!!)
                                        ref2.addValueEventListener(object : ValueEventListener {
                                            override fun onCancelled(p3: DatabaseError) {
                                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                            }

                                            override fun onDataChange(p3: DataSnapshot) {
                                                //ServicioMapa.latitudes.clear()
                                                //ServicioMapa.longitudes.clear()
                                                for (librox in p3.children) {
                                                    datosHijo = p3.getValue(Libro::class.java) as Libro

                                                    if (TextUtils.equals(datosHijo.idLibro, idLibroRecibido)) {
                                                        txtIcbnMostrar.setText(datosHijo.icbn.toString())
                                                        txtEditorialMostrar.setText(datosHijo.editorial.toString())
                                                        txtEdicionMostrar.setText(datosHijo.numEdicion.toString())
                                                        txtFechaMostrar.setText(datosHijo.fechaPublicacion.toString())
                                                        txtNombreLibroMostrar.setText(datosHijo.nombreLibro.toString())
                                                        txtNumPagMostrar.setText(datosHijo.numeroPaginas.toString())
                                                        txtLatitudMostrar.setText(datosHijo.latitud.toString())
                                                        txtLongitudMostrar.setText(datosHijo.longitud.toString())

                                                    }


                                                }

                                            }

                                        })

                                    }


                                }
                            }
                        })

                    }
                }
            }
        })

        iraMenu()

    }

    fun iraMenu(){
        val intentMostrar = Intent(this, MenuActivity::class.java)
        this.startActivity(intentMostrar)
        this.finish()
    }

}


