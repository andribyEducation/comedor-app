package views.home;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import components.Button.RoundedButton;

public class Home extends JFrame {

    public Home() {
        setTitle("Comedor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());


        JButton btn;
        btn = new RoundedButton("Registrar", false);
        btn.setBounds(50, 50, 200, 50);
      
        add(btn);
    }
}