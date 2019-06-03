package com.example.mjg70.examen

class BDAutores{
    companion object {
        val LST_AUTOR:ArrayList<Autor> = ArrayList();
        var serial:Int= 1;
        var nombreUsuario:String = "";

        fun guardarUsuario(nombre:String){
            this.nombreUsuario = nombre;
        }

        fun agregarAutor(autor: Autor):ArrayList<Autor>{
            autor.id = serial
            serial++
            LST_AUTOR.add(autor)
            return LST_AUTOR
        }

        fun mostrarAutor(): List<Autor> {
            return this.LST_AUTOR
        }

        fun eliminarAutor(id:Int){
            this.LST_AUTOR.removeAll{ it.id == id }
        }

        fun actualizarAutor(autor: Autor){
            val indice = this.LST_AUTOR.indexOfFirst { it.id == autor.id }
            this.LST_AUTOR[indice] = autor
        }

    }

}