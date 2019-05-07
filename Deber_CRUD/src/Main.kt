import java.awt.Color
import javax.swing.JButton
import javax.swing.JFrame

// GUI de KOTLIN

    fun main(){
        val frame1 = JFrame("CAMPEONES LoL")

        frame1.contentPane = Ventana().panel1
        frame1.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame1.setSize(600, 600)
        frame1.isResizable = false
        frame1.setLocationRelativeTo(null)
        frame1.isVisible = true



}