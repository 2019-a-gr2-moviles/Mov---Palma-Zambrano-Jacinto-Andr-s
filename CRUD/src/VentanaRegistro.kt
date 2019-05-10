import java.io.IOException
import java.util.ArrayList

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.table.DefaultTableModel

import CampeonR
import Campeon
import java.awt.event.ActionEvent
import java.awt.event.ActionListener


class VentanaRegistro : JFrame(), ActionListener {

    private val labelTitulo: JLabel
    private val labelTabla1: JLabel? = null
    private val labelTabla2: JLabel
    private val textNombCamp: JTextField
    private val textTipoCamp: JTextField
    private val textEdad: JTextField? = null
    private val textRolCamp: JTextField
    private val textProfesion: JTextField? = null
    private val cod: JLabel
    private val nombre: JLabel
    private val edad: JLabel? = null
    private val telefono: JLabel
    private val profesion: JLabel? = null
    private val botonGuardar: JButton
    private val botonCancelar: JButton
    private val botonActualizar: JButton
    private val botonEliminar: JButton
    internal var mitabla1: JTable? = null
    internal var mibarra1: JScrollPane
    internal var miBarra2: JScrollPane

    init {
        botonGuardar = JButton()
        botonGuardar.setBounds(110, 200, 120, 25)
        botonGuardar.text = "Registrar"

        botonActualizar = JButton()
        botonActualizar.setBounds(110, 250, 120, 25)
        botonActualizar.text = "Actualizar"

        botonEliminar = JButton()
        botonEliminar.setBounds(250, 250, 120, 25)
        botonEliminar.text = "Eliminar"

        botonCancelar = JButton()
        botonCancelar.setBounds(250, 200, 120, 25)
        botonCancelar.text = "Cancelar"

        labelTitulo = JLabel()
        labelTitulo.text = "REGISTRO DE CAMPEONES"
        labelTitulo.setBounds(120, 20, 380, 30)
        labelTitulo.font = java.awt.Font("Verdana", 1, 18)


        labelTabla2 = JLabel()
        labelTabla2.text = "Tabla Usando Table Model"
        labelTabla2.setBounds(40, 430, 380, 50)

        cod = JLabel()
        cod.text = "Nombre"
        cod.setBounds(20, 80, 80, 25)
        add(cod)

        nombre = JLabel()
        nombre.text = "Tipo"
        nombre.setBounds(20, 120, 80, 25)
        add(nombre)

        telefono = JLabel()
        telefono.text = "Rol"
        telefono.setBounds(20, 160, 80, 25)
        add(telefono)

        textNombCamp = JTextField()
        textNombCamp.setBounds(80, 80, 80, 25)
        add(textNombCamp)

        textTipoCamp = JTextField()
        textTipoCamp.setBounds(80, 120, 190, 25)
        add(textTipoCamp)

        textRolCamp = JTextField()
        textRolCamp.setBounds(80, 160, 80, 25)
        add(textRolCamp)

        botonGuardar.addActionListener(this)
        botonCancelar.addActionListener(this)
        botonEliminar.addActionListener(this)
        botonActualizar.addActionListener(this)
        // ////////////////////////////////////////////
        mibarra1 = JScrollPane()
        mibarra1.setBounds(40, 300, 400, 130)


        miBarra2 = JScrollPane()
        miBarra2.setBounds(40, 480, 400, 130)
        mostrarDatosConTableModel()

        add(botonCancelar)
        add(botonGuardar)
        add(botonActualizar)
        add(botonEliminar)
        add(labelTitulo)
        add(labelTabla2)
        add(mibarra1)
        add(miBarra2)
        limpiar()
        setSize(480, 650)
        title = "CoDejaVu : Componentes JTable"
        setLocationRelativeTo(null)
        isResizable = false
        layout = null
    }

    private fun mostrarDatosConTableModel() {
        val model: DefaultTableModel
        model = DefaultTableModel()
        val miCampeonR = CampeonR()
        try {
            miCampeonR.buscarUsuariosConTableModel(model)
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }


    }

    private fun limpiar() {
        textNombCamp.text = ""
        textTipoCamp.text = ""
        textRolCamp.text = ""
    }

    override fun actionPerformed(e: ActionEvent) {
        if (e.source === botonGuardar) {

            val miCampeon = CampeonR()
            try {
                val campeon = Campeon(
                    textNombCamp.text, textTipoCamp.text, textRolCamp
                        .text
                )
                miCampeon.registrarCampeon(campeon)
            } catch (ex: Exception) {
                JOptionPane.showMessageDialog(
                    null,
                    "Error en el Ingreso de Datos", "Error",
                    JOptionPane.ERROR_MESSAGE
                )
            } finally {
                mostrarDatosConTableModel()
                limpiar()
            }
        }
        if (e.source === botonCancelar) {
            limpiar()
        }
        if (e.source === botonActualizar) {
            val miCampeon = CampeonR()
            try {
                val campeon = Campeon(
                    textNombCamp.text, textTipoCamp.text, textRolCamp
                        .text
                )
                miCampeon.actualizarCampeon(campeon)
            } catch (ex: Exception) {
                JOptionPane.showMessageDialog(
                    null,
                    "Error en el Ingreso de Datos", "Error",
                    JOptionPane.ERROR_MESSAGE
                )
            } finally {
                mostrarDatosConTableModel()
                limpiar()
            }
        }
        if (e.source === botonEliminar) {
            val miCampeon = CampeonR()
            try {
                val campeon = Campeon(
                    textNombCamp.text, textTipoCamp.text, textRolCamp
                        .text
                )
                miCampeon.eliminarCampeon(campeon)
            } catch (ex: Exception) {
                JOptionPane.showMessageDialog(
                    null,
                    "Error en el Ingreso de Datos", "Error",
                    JOptionPane.ERROR_MESSAGE
                )
            } finally {
                mostrarDatosConTableModel()
                limpiar()
            }
        }

    }

}