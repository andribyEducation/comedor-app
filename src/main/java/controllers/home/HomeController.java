package controllers.home;

import views.home.Home;
import views.login.LoginView;
import views.registroComensalView.RegistroComensalView;

import javax.swing.*;

import controllers.LoginController;
import controllers.RegistroComensalController;

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
            redirigirALogin();
        } else if (e.getSource() == homeView.getBtnRegistrarse()) {
            redirigirARegistro();
        }
    }

    // Nueva función para redirigir al login
    private void redirigirALogin() {
        homeView.setVisible(false);
        LoginView ingresarView = new LoginView();
        new LoginController(ingresarView);
        ingresarView.setVisible(true);
    }

    // Nueva función para redirigir al registro
    private void redirigirARegistro() {
        homeView.setVisible(false);
        RegistroComensalView registroView = new RegistroComensalView();
        new RegistroComensalController(registroView);
        registroView.setVisible(true);
    }
}
