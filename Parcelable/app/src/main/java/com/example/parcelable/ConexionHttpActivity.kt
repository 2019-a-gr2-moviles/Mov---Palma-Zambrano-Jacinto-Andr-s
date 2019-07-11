package com.example.parcelable


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.beust.klaxon.Klaxon
import com.beust.klaxon.KlaxonDoc
import com.github.kittinunf.fuel.httpGet
import java.util.*
import com.github.kittinunf.result.Result.*
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class ConexionHttpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conexion_http)
        val json = """
            [

            {
                "usuariosDeEmpresas": [
                 {
                    "createdAt": 1561663858454,
                    "updatedAt": 1561663858454,
                    "id": 1,
                    "nombre": "Jorge Zambrano",
                    "fkEmpresa": 1
                }
                ],
                "createdAt": 1561663858454,
                "updatedAt": 1561663858454,
                "id": 3,
                "nombre": "kfc"
            }]
        """.trimIndent()
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
                    "ID ${it.id}"
                )

                Log.i(
                    "http",
                    "Fecha ${it.fechaCreacion}"
                )
                it.usuariosDeEmpresas.forEach {
                    Log.i(
                        "http",
                        "Nombre Usuario ${it.nombre}"
                    )
                    Log.i(
                        "http",
                        "FK Empresa ${it.fkEmpresa}"
                    )
                }

            }
        } catch (e: Exception) {
            Log.i("http", "${e.message}")
            Log.i(
                "http",
                "Error instanciando la empresa"
            )
        }

        val url = "http://192.168.43.146:1337/empresa/3"
        url
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Success -> {
                        val data = result.get()
                        Log.i("http", "Data: ${data}")

                        val empresaParseada = Klaxon()
                            .parse<Empresa>(data)
                        if (empresaParseada != null) {
                            Log.i("http", " iiiiiiiiiiiiiiiiiiii ")
                            Log.i("http", "${empresaParseada.nombre} ")
                            Log.i("http", "${empresaParseada.id} ")
                        }
                    }
                }


            }

        val urlCrearEmpresa = "http://192.168.43.146:1337/empresa"
        val parametrosCrearEmpresa= listOf(
            "nombre" to "Jorge"
        )
             urlCrearEmpresa
            .httpPost(parametrosCrearEmpresa)
                 .responseString { request, response, result ->
                             when (result) {
                                 is Failure -> {
                                     val ex = result.getException()
                                     Log.i("http", "Error: ${ex}")
                                 }
                                 is Success -> {
                                     val empresaString=result.get()
                                     Log.i("http", "${empresaString}")

                                 }
                             }
                         }
                     }


                 }



