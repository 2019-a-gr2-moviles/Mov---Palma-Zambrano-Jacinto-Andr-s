package com.example.dr.examen_primerbimestre

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dr.examen_primerbimestre.MenuActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_ingresar_autor.*

class IngresarAutorActivity : AppCompatActivity() {
    var usuario:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_autor)
        usuario = intent.getStringExtra("usuario").toString()


        btnAceptar.setOnClickListener { aceptarIngreso() }


        btnCancelar.setOnClickListener { cancelarIngreso()}
    }

    fun cancelarIngreso(){
        val intentCancelar= Intent(this, MenuActivity::class.java)
        intentCancelar.putExtra("usuario", usuario)
        startActivity(intentCancelar)
    }

    fun aceptarIngreso(){

        val   nombres = txtNombreAutor.text.toString().trim()
        val  apellidos = txtApellidosAutor.text.toString().trim()
        val  fechaNacimiento = txtFechaNacimientoAutor.text.toString().trim()
        val   numeroLibros = numLibros.text.toString().toInt()
        val   ecuatoriano = txtEcuatoriano.text.toString().trim()

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
        val Autorid = referenceData.push().key
        if(Autorid!=null){
            val autorNuevo = Autor(Autorid,nombres,apellidos,fechaNacimiento,numeroLibros, ecuatoriano)
            referenceData.child(Autorid).setValue(autorNuevo).addOnCompleteListener{
                Toast.makeText(this, "Ingreso exitoso "+usuario, Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Valor de ID nulo ", Toast.LENGTH_SHORT).show()
        }

        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }





    override fun onBackPressed() {

        val intentMenu = Intent(this, MenuActivity::class.java)
        intentMenu.putExtra("usuario", usuario)
        startActivity(intentMenu)
    }
}
