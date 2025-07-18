package views.home;

import javax.swing.*;
import java.awt.*;
import components.Button.RoundedButton;

public class Home extends JFrame {
    private JPanel panel;
    private JButton btnIngresar;
    private JButton btnRegistrarse;
    private JLabel olvideContraseña;
    private Dimension btnSize = new Dimension(400, 50);

    public Home() {
        setTitle("Comedor");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        agregarPanel();
    }

    private void agregarLogo() {
        java.net.URL logoUrl = getClass().getResource("/assets/logos/logoucv.png");
        if (logoUrl != null) {
            ImageIcon originalIcon = new ImageIcon(logoUrl);
        // Define el nuevo ancho y alto según necesites.
        int newSize = 200;
        Image scaledImage = originalIcon.getImage().getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logo = new JLabel(scaledIcon);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(logo);
        } else {
            System.err.println("Couldn't find resource: /assets/logos/Logo_Universidad_Central_de_Venezuela.svg.png");
            JLabel logo = new JLabel("Logo not found");
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(logo);
        }
    }

    private void agregarOlvideContraseña() {
        olvideContraseña = new JLabel("¿Olvidaste tu contraseña?");
        olvideContraseña.setFont(new Font("Inter", Font.PLAIN, 16));
        olvideContraseña.setAlignmentX(Component.CENTER_ALIGNMENT);
        olvideContraseña.setForeground(new Color(0, 51, 102));
        olvideContraseña.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(Box.createVerticalStrut(100));
        panel.add(olvideContraseña);
    }

    private void agregarBtnRegistrarse() {
        btnRegistrarse = new RoundedButton("Registrarse", false);
        btnRegistrarse.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrarse.setPreferredSize(btnSize);
        btnRegistrarse.setMaximumSize(btnSize);
        btnRegistrarse.setFont(new Font("Inter", Font.BOLD, 20));
        panel.add(Box.createVerticalStrut(20)); // Espacio vertical entre componentes.
        panel.add(btnRegistrarse);
        agregarOlvideContraseña();
    }

    private void agregarBtnIngresar() {
        btnIngresar = new RoundedButton("Ingresar", false);
        btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIngresar.setPreferredSize(btnSize);
        btnIngresar.setMaximumSize(btnSize);
        btnIngresar.setFont(new Font("Inter", Font.BOLD, 20));
        panel.add(Box.createVerticalStrut(20)); // Espacio vertical entre componentes.
        panel.add(btnIngresar);
        agregarBtnRegistrarse();
    }


    private void agregarPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertical
        panel.setBackground(Color.WHITE); // Set background to white
        this.getContentPane().add(panel);

        panel.add(Box.createVerticalStrut(20)); // Add some padding from the top
        agregarLogo();
        // Placeholder for the arrow. You might need to create a separate method for this.
        // For example: agregarArrow();
        panel.add(Box.createVerticalStrut(50));

        JLabel titulo = new JLabel("Bienvenido al Comedor UCV", SwingConstants.CENTER);
        titulo.setFont(new Font("Inter", Font.BOLD, 38));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);

        panel.add(Box.createVerticalStrut(50));

        agregarBtnIngresar();

        panel.add(Box.createVerticalGlue());
    }

    public JButton getBtnIngresar() {
        return btnIngresar;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public JLabel getOlvideContraseña() {
        return olvideContraseña;
    }
}
