package com.example.parcelable

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_respuesta.*

class IntentRespuesta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_respuesta)
        btn_enviar_intent.setOnClickListener(){
            enviarIntentConRespuseta()
        }
        btn_respuseta_propia.setOnClickListener(){
            enviarIntentConRespuestaPropia()
        }

    }
    fun enviarIntentConRespuestaPropia(){
        val intentPropio=Intent(this, Resultado::class.java)
        this.startActivityForResult(intentPropio,305)


    }
    fun enviarIntentConRespuseta(){
        val intentConRespuseta= Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        this.startActivityForResult(intentConRespuseta,304)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode){
            RESULT_OK->{
                Log.i("intent-respueta","LO LOGRAMOS! ${Activity.RESULT_OK}")

                when(requestCode){
                    304->{
                        Log.i("intent-respueta","Contacto llegó")
                        val uri=data?.data
                        var cursor= contentResolver.query(uri,
                            null,
                            null,
                            null,
                            null)
                        cursor.moveToFirst()
                        val indiceTelefono=cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono= cursor.getString(indiceTelefono)
                        Log.i("intent-respuesta","El telefono es: $telefono")
                    }
                    305->{
                        val nombre=data?.getStringExtra("nombreUsuario")
                        val edad=data?.getIntExtra("edadUsuario",0)
                        Log.i("intent-respueta","Nombre: ${nombre} Edad: ${edad}" )
                    }
                }
            }
            RESULT_CANCELED->{
                Log.i("intent-respueta","No escogió :c")
            }

        }

    }
}
