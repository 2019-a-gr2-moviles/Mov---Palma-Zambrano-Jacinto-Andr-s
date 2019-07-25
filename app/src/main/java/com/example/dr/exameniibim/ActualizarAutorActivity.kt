package com.example.dr.exameniibim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_actualizar_autor.*

class ActualizarAutorActivity : AppCompatActivity() {

    var usuario: String = ""
    var id: String = ""
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_autor)

        usuario = intent.getStringExtra("usuario").toString()
        id = intent.getStringExtra("id").toString()
        ref = FirebaseDatabase.getInstance().getReference("Autores")

        //Agrega los valores del objeto en los campos
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {

                    for (autor in p0.children) {
                        val aut = autor.getValue(Autor::class.java)
                       if(aut!!.id == id){
                           txtNombreAutor.setText(aut.nombres)
                           txtApellidosAutor.setText(aut.apellidos)
                           txtFechaNacimientoAutor.setText(aut.fechaNacimiento)
                           numLibros.setText(aut.numeroLibros.toString())
                           txtEcuatorianoAct.setText(aut.ecuatoriano)
                       }
                    }
                }
            }
        })

        btnActualizar.setOnClickListener {
            actualizarAutor()
        }

        btnEliminar.setOnClickListener {
            eliminarAutor()
        }

        btnCrearLibro.setOnClickListener {
            crearLibro()
        }

    }

    fun crearLibro() {
        val intentCrearLibro = Intent(this, IngresarLibroActivity::class.java)
        intentCrearLibro.putExtra("usuario", usuario)
        intentCrearLibro.putExtra("id", id)
        startActivity(intentCrearLibro)
        finish()
    }

    private fun eliminarAutor() {
        val drAutor : DatabaseReference = FirebaseDatabase.getInstance().getReference("Autores").child(id)

        drAutor.removeValue()
        Toast.makeText(this,"${usuario} ha eliminado un Autor y su contenido",Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, ConsultarAutorActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    private fun actualizarAutor() {
        val   nombres = txtNombreAutor.text.toString().trim()
        val  apellidos = txtApellidosAutor.text.toString().trim()
        val  fechaNacimiento = txtFechaNacimientoAutor.text.toString().trim()
        val   numeroLibros = numLibros.text.toString().toInt()
        val   ecuatoriano = txtEcuatorianoAct.text.toString().trim()

        if(TextUtils.isEmpty(nombres) ){
            txtNombreAutor.error = "Ingrese el nombre del autor"
            return
        }

        if (TextUtils.isEmpty(apellidos)){
            txtApellidosAutor.error = "Ingrese el apellido del autor"
            return
        }

        if(TextUtils.isEmpty(fechaNacimiento) ) {
            txtFechaNacimientoAutor.error = "Ingrese la fecha de nacimiento del autor"
            return
        }

        val referenceData = FirebaseDatabase.getInstance().getReference("Autores")
        val Autorid = id
        if(Autorid!=null){
            val autorNuevo = Autor(Autorid,nombres,apellidos,fechaNacimiento,numeroLibros, ecuatoriano)
            referenceData.child(Autorid).setValue(autorNuevo).addOnCompleteListener{
                Toast.makeText(this, "Actualización exitosa "+usuario, Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Valor de ID nulo ", Toast.LENGTH_SHORT).show()
        }

        val retorno = Intent(this, ConsultarAutorActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }
}
