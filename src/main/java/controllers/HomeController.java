package controllers;

import views.home.Home;
import javax.swing.JOptionPane;
import java.awt.event.*;


public class HomeController implements ActionListener {
    private Home homeView;

    public HomeController(Home homeView) {
        this.homeView = homeView;
        // Añadir listeners a los botones de la vista
        this.homeView.getBtnIngresar().addActionListener(this);
        this.homeView.getBtnRegistrarse().addActionListener(this);
        this.homeView.getOlvideContraseña().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Lógica para el JLabel "Olvidaste tu contraseña"
                JOptionPane.showMessageDialog(homeView, "Funcionalidad de recuperación de contraseña no implementada.");
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeView.getBtnIngresar()) {
            // Lógica para el botón Ingresar
            JOptionPane.showMessageDialog(homeView, "Botón Ingresar presionado (desde el controlador)");
        } else if (e.getSource() == homeView.getBtnRegistrarse()) {
            // Lógica para el botón Registrarse
            JOptionPane.showMessageDialog(homeView, "Botón Registrarse presionado (desde el controlador)");
        }
    }


}
