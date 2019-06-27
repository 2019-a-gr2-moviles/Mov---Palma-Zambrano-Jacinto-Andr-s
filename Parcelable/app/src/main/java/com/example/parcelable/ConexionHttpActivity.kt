package com.example.parcelable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import java.lang.Exception
import com.github.kittinunf.result.Result.*
import com.github.kittinunf.fuel.httpGet
import java.util.*

class ConexionHttpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conexion_http)

        /*val json: String = """
            [
            {

            "createdAt": 1561663617636,
            "updatedAt": 1561663617636,
            "id": 1,
            "nombre": "Manticore Labs"

            }
]
        """.trimIndent()
*///JSON con uno solo


        val json = """
            [
                {
                  "usuariosDeEmpresa": [
                      {
                          "createdAt": 1561663617636,
                          "updatedAt": 1561663617636,
                          "id": 1,
                          "nombre": "Adrian",
                          "fkEmpresa": 1,
                      }
                  ],
                  "createdAt": 1561663617636,
                  "updatedAt": 1561663617636,
                  "id": 1,
                  "nombre": "Manticore Labs"
                }
            ]
        """.trimIndent()
/* Try-catch original
        try {
            val empresaInstancia = Klaxon().parse<Empresa>(json)

            Log.i("http", "Nombre ${empresaInstancia?.nombre}")
            Log.i("http", "ID ${empresaInstancia?.id}")

            if (empresaInstancia != null) {
                Log.i("http", "Fecha ${Date(empresaInstancia.createdAt)}")
            }

        } catch (e: Exception) {
            Log.i("http", "Error instanciando la empresa")
        }*/
// Try catch mejorado para arreglos
        try {
            val empresaInstancia = Klaxon()
                .parseArray<Empresa>(json)
            empresaInstancia?.forEach {
                Log.i(
                    "http",
                    "Nombre ${it.nombre}"
                )

                Log.i(
                    "http",
                    "Id ${it.id}"
                )

                Log.i(
                    "http",
                    "Fecha ${it.fechaCreacion}"
                )

                it.usuariosDeEmpresa.forEach {
                    Log.i(
                        "http",
                        "Nombre ${it.nombre}"
                    )
                    Log.i(
                        "http",
                        "FK ${it.fkEmpresa}"
                    )

                }
            }
        } catch (e:Exception){
            Log.i("http","${e.message}")
            Log.i("http",
                "Error instanciando la empresa")
        }


        val url = "http://172.31.104.107:1337/empresa/1"

            url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Failure -> {
                        val ex = result.getException()
                        Log.i("http","Error: ${ex.message}")
                    }
                    is Success -> {
                        val data = result.get()
                        Log.i("http","Data: ${data}")
                    }
                }
            }

    }
}
