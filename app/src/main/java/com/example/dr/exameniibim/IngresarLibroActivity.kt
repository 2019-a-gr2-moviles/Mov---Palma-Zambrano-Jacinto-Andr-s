package com.example.dr.exameniibim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_ingresar_libro.*

class IngresarLibroActivity : AppCompatActivity() {
    var usuario:String = ""
    var id:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_libro)
         usuario = intent.getStringExtra("usuario")
         id = intent.getStringExtra("id")


        btnGuardar.setOnClickListener {
            crearLibro()
        }
    }

    private fun crearLibro() {

            val   ICBN = txtICBN.text.toString().toInt()
            val  nombreLibro = txtNombreLibro.text.toString().trim()
            val  numPaginas = txtNumPaginas.text.toString().toInt()
            val   fechaPublicacion = txtfechaPublicacion.text.toString()
            val   editorial = txtEditorial.text.toString().trim()
            val edicion = txtnumEdicion.text.toString().toInt()
            val latitud = txtLongitud.text.toString().toFloat()
            val longitud = txtLongitud.text.toString().toFloat()

            val Autorid = id

            val referenceData = FirebaseDatabase.getInstance().getReference("Libros").child(Autorid)
            val Libroid = referenceData.push().key
            if(Libroid!=null){
                val libroNuevo = Libro(Libroid,ICBN,nombreLibro,numPaginas,editorial,fechaPublicacion,edicion,
                    latitud,longitud)
                referenceData.child(Libroid).setValue(libroNuevo).addOnCompleteListener{
                    Toast.makeText(this, "Ingreso libro nuevo "+usuario, Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "Valor de ID nulo ", Toast.LENGTH_SHORT).show()
            }

            val retorno = Intent(this, ConsultarAutorActivity::class.java)
            retorno.putExtra("usuario", usuario)
            retorno.putExtra("id",id)
            startActivity(retorno)

    }
}
