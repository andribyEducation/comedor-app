package views.comensal.menuDelDia;

import javax.swing.*;
import java.awt.*;

public class menuDelDia extends JFrame {

    public menuDelDia(String dia) {
        setTitle("Menú del Día - " + dia);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Solo cierra esta ventana
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Fuente Inter directamente
        Font interFont = new Font("Inter", Font.PLAIN, 20);

        JLabel titulo = new JLabel("Menú del Día: " + dia);
        titulo.setFont(interFont.deriveFont(Font.BOLD, 28f));
        titulo.setBounds(50, 30, 700, 40);
        titulo.setForeground(Color.BLACK);
        add(titulo);

        JTextArea menuArea = new JTextArea();
        menuArea.setBounds(50, 90, 700, 400);
        menuArea.setFont(interFont);
        menuArea.setLineWrap(true);
        menuArea.setWrapStyleWord(true);
        menuArea.setEditable(false);
        menuArea.setText("Aquí se mostraría el menú correspondiente a " + dia + ".\n\n• Entrada: ...\n• Plato principal: ...\n• Postre: ...");

        add(menuArea);
    }
}
