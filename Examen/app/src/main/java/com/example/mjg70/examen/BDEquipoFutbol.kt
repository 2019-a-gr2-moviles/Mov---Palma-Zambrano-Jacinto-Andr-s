package com.example.mjg70.examen

class BDEquipoFutbol{
    companion object {
        val LST_EQUIPO:ArrayList<EquipoFutbol> = ArrayList();
        var serial:Int= 1;
        var nombreUsuario:String = "";

        fun guardarUsuario(nombre:String){
            this.nombreUsuario = nombre;
        }

        fun agregarEquipo(equipo: EquipoFutbol):ArrayList<EquipoFutbol>{
            equipo.id = serial
            serial++
            LST_EQUIPO.add(equipo)
            return LST_EQUIPO
        }

        fun mostrarEquipo(): List<EquipoFutbol> {
            return this.LST_EQUIPO
        }

        fun eliminarEquipo(id:Int){
            this.LST_EQUIPO.removeAll{ it.id == id }
        }

        fun actualizarEquipo(equipo: EquipoFutbol){
            val indice = this.LST_EQUIPO.indexOfFirst { it.id == equipo.id }
            this.LST_EQUIPO[indice] = equipo
        }

    }

}