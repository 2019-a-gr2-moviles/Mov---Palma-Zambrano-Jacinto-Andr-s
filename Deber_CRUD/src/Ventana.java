import javax.swing.*;
import java.awt.*;

public class Ventana {
    public JPanel panel1;
    private JButton btnAgregar;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public  Ventana(){
       panel1.setBackground(Color.darkGray);
    }
    public  static void main(String []args){


        JFrame frame1 = new JFrame("CAMPEONES LoL");


        frame1.setContentPane(new Ventana().panel1);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(600,600);
        frame1.setResizable(false);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);




    }
}
