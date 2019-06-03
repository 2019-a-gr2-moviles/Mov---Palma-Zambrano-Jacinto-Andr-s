package com.example.mjg70.examen

class BDLibros {
    companion object {
        val LST_LIBRO:ArrayList<Libro> = ArrayList();
        var serial:Int= 1;

        fun agregarLibro(libro: Libro):ArrayList<Libro>{
            libro.id = serial
            serial++
            LST_LIBRO.add(libro)
            return LST_LIBRO
        }

        fun mostrarJugador(padreId:Int): List<Libro> {
            val lstFiltradaJugador = this.LST_LIBRO.filter { it.autorId ==  padreId}
            return lstFiltradaJugador
        }

        fun eliminarJugador(id:Int){
            this.LST_LIBRO.removeAll{ it.id == id }
        }

        fun actualizarJugador(libro: Libro){
            val indice = this.LST_LIBRO.indexOfFirst { it.id == libro.id }
            this.LST_LIBRO[indice] = libro
        }

    }

}