package com.example.parcelable

import java.util.*

class Empresa (

    var id: Int,
    var nombre: String,
    var createdAt: Long,
    var updatedAt: Long,
    var usuariosDeEmpresa: ArrayList<UsuarioEmpresa>
){
    var fechaCreacion : Date
    var fechaActualizacion: Date


    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }

}