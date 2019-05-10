import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.util.ArrayList

import javax.swing.JOptionPane
import javax.swing.table.DefaultTableModel

import Campeon

class CampeonR {

    fun registrarCampeon(miCampeon:Campeon) {
        var fichero:FileWriter? = null
        var pw:PrintWriter? = null
        try
        {
            fichero = FileWriter("C:\\Users\\Dario\\Documents\\Aplicaciones_Moviles\\Mov-Palma-Zambrano-Jacinto-Andres\\CRUD\\src\\champs.txt", true)
            pw = PrintWriter(fichero!!)
            pw!!.println(
                miCampeon.nombreCampeon + ";"
                        + miCampeon.tipoCampeon + ";"
                        + miCampeon.rolCampeon
            )
        }
        catch (e:Exception) {
            e.printStackTrace()
        }
        finally
        {
            try
            {
                if (null != fichero)
                    fichero!!.close()
            }
            catch (e2:Exception) {
                e2.printStackTrace()
            }

        }
    }

    @Throws(IOException::class)
    fun eliminarCampeon(miCampeon:Campeon) {
        var archivo:File? = null
        var fr:FileReader? = null
        var br:BufferedReader? = null
        val lstRespaldo = ArrayList<String>()
        archivo = File("C:\\Users\\Dario\\Documents\\Aplicaciones_Moviles\\Mov-Palma-Zambrano-Jacinto-Andres\\CRUD\\src\\champs.txt")
        fr = FileReader(archivo!!)
        br = BufferedReader(fr!!)
        var linea:String
        //linea = br.readLine()
        for(line in br.lines()){
            lstRespaldo.add(line)
        }
        br!!.close()
        val bw = BufferedWriter(FileWriter("C:\\Users\\Dario\\Documents\\Aplicaciones_Moviles\\Mov-Palma-Zambrano-Jacinto-Andres\\CRUD\\src\\champs.txt"))
        bw.write("")
        bw.close()
        for (i in lstRespaldo.indices)
        {
            val parts = lstRespaldo[i].split((";").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (parts[0].trim { it <= ' ' } == miCampeon.nombreCampeon)
            {
                lstRespaldo.removeAt(i)
            }
        }
        val bw1 = BufferedWriter(FileWriter("C:\\Users\\Dario\\Documents\\Aplicaciones_Moviles\\Mov-Palma-Zambrano-Jacinto-Andres\\CRUD\\src\\champs.txt"))
        for (hola in lstRespaldo)
        {
            bw1.write(hola)
            bw1.write("\n")
        }
        bw1.close()
    }

    @Throws(IOException::class)
    fun actualizarCampeon(miCampeon:Campeon) {
        var archivo:File? = null
        var fr:FileReader? = null
        var br:BufferedReader? = null
        val lstRespaldo = ArrayList<String>()
        archivo = File("C:\\Users\\Dario\\Documents\\Aplicaciones_Moviles\\Mov-Palma-Zambrano-Jacinto-Andres\\CRUD\\src\\champs.txt")
        fr = FileReader(archivo!!)
        br = BufferedReader(fr!!)
        var linea:String
        //linea = br.readLine()
        for(line in br.lines()){
            lstRespaldo.add(line)
        }
        br!!.close()
        val bw = BufferedWriter(FileWriter("C:\\Users\\Dario\\Documents\\Aplicaciones_Moviles\\Mov-Palma-Zambrano-Jacinto-Andres\\CRUD\\src\\champs.txt"))
        bw.write("")
        bw.close()
        var reemplazo = ""
        for (i in lstRespaldo.indices)
        {
            val parts = lstRespaldo[i].split((";").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (parts[0].trim { it <= ' ' } == miCampeon.nombreCampeon)
            {
                reemplazo = (miCampeon.nombreCampeon + ";"
                        + miCampeon.tipoCampeon + ";"
                        + miCampeon.rolCampeon)
                lstRespaldo.removeAt(i)
            }
        }
        lstRespaldo.add(reemplazo)
        val bw1 = BufferedWriter(FileWriter("C:\\Users\\Dario\\Documents\\Aplicaciones_Moviles\\Mov-Palma-Zambrano-Jacinto-Andres\\CRUD\\src\\champs.txt"))
        for (hola in lstRespaldo)
        {
            bw1.write(hola)
            bw1.write("\n")
        }
        bw1.close()
    }

    @Throws(IOException::class)
    fun buscarUsuariosConTableModel(model:DefaultTableModel) {

        var archivo:File? = null
        var fr:FileReader? = null
        var br:BufferedReader? = null
        val fila = arrayOfNulls<Any>(3)
        archivo = File("C:\\Users\\Dario\\Documents\\Aplicaciones_Moviles\\Mov-Palma-Zambrano-Jacinto-Andres\\CRUD\\src\\champs.txt")
        fr = FileReader(archivo!!)
        br = BufferedReader(fr!!)
        var linea:String
        //linea = br.readLine()
        for(line in br.lines())
        {
            val parts = line.split((";").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (i in 0..2)
                fila[i] = parts[i]
            model.addRow(fila)
        }
        br!!.close()
    }


}
