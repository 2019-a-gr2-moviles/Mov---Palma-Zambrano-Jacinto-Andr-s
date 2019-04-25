fun main(args : Array<String>){
    /*
    *Comentario
     */

    // Variables
    //QUE ES MUTAR?? CAMBIAR??
    //Mutables
    var nombre = "Jacinto"
    nombre = "Andres"
    //Inmutables
    val nombreI = "Jacinto"
    //nombreI = "Andres"

    //Tipos de Datos
    val apellido: String = "Palma"
    val edad: Int = 25
    val sueldo: Double = 1.21
    val casado = false
    val profesor= true
    val hijos = null

val numerito = Numero(1)

}


class Usuario(val cedula:String){
    public var nombre:String =""
    public var apellido:String = ""

    constructor(cedula:String, apellido:String):this(cedula){
        this.apellido = apellido
    }
}

class UsuarioKT(public var nombre:String, public var apellido:String, private var id:Int, protected var id2:Int){

    fun hola():String{ //  Unit es un void
        return this.apellido
    }
    private fun hola2(){}
    protected fun hola3(){}

    companion object { // define metodos y propiedades
        val gravedad = 10.5
        fun correr(){
            println("Estoy corriendo a $gravedad")
        }
    }

}
class BaseDeDatos{
    companion object {
        val usuarios = arrayListOf(1,2,3)
        fun agregarUsuario(usuario:Int){
            this.usuarios.add(usuario)
        }
        fun eliminarUsuario(usuario:Int){

        }
    }
}
fun aa(){
    UsuarioKT.gravedad //propiedad ~ estática
    UsuarioKT.correr() //método ~ etático
}

class Numero(var numero:Int){

    constructor(numeroString:String):this(numeroString.toInt()){
        println("CONSTRUCTOR")
    }
    init { //el bloque init  pertenece al primer constructr
        println("INIT")
        this.numero
    }
}

// Clase abstracta es una clase que no se va a instanciar por que sirve para otras clases
abstract class  Numeros(var numeroUno:Int, var numeroDos:Int){

}
class Suma(numeroUnos: Int, numeroDoss:Int):Numeros(numeroUnos,numeroDoss){

}
fun cc(){
    val a = Suma(1,2)
    a.numeroUno
}

fun presley(requerido:Int, opcional:Int=1, nulo:UsuarioKT?){
    if(nulo != null){
        nulo.nombre
    }
    val nombresito:String? = nulo?.nombre.toString()
}
 fun cddd(){
     /*presley(1, nulo = 0) // Named Parameters
     presley(1, 1, 0) // Named Parameters
     presley(1, 1, null ) // Named Parameters*/
 }