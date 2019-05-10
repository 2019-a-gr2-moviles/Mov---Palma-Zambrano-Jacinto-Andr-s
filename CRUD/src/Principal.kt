import VentanaRegistro

object Principal {

    /**
     * Llama la ventana principal
     * @param args
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val miVentanaRegistro: VentanaRegistro
        miVentanaRegistro = VentanaRegistro()
        miVentanaRegistro.setVisible(true)
    }

}
