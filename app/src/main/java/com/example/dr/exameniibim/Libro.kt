package com.example.dr.exameniibim

class Libro(val idLibro:String,
            val icbn:Int,
            val nombreLibro:String,
            val numeroPaginas:Int,
            val editorial:String,
            val fechaPublicacion:String,
            val numEdicion:Int,
            val latitud:Float,
            val longitud:Float) {

    constructor(): this("", 0, "", 0, "", "", 0, 0.0F, 0.0F)
}

