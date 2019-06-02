package com.example.mjg70.examen

class BDAutores{
    companion object {
        val LST_EQUIPO:ArrayList<Autor> = ArrayList();
        var serial:Int= 1;
        var nombreUsuario:String = "";

        fun guardarUsuario(nombre:String){
            this.nombreUsuario = nombre;
        }

        fun agregarEquipo(equipo: Autor):ArrayList<Autor>{
            equipo.id = serial
            serial++
            LST_EQUIPO.add(equipo)
            return LST_EQUIPO
        }

        fun mostrarEquipo(): List<Autor> {
            return this.LST_EQUIPO
        }

        fun eliminarEquipo(id:Int){
            this.LST_EQUIPO.removeAll{ it.id == id }
        }

        fun actualizarEquipo(equipo: Autor){
            val indice = this.LST_EQUIPO.indexOfFirst { it.id == equipo.id }
            this.LST_EQUIPO[indice] = equipo
        }

    }

}