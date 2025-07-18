package controllers;

import views.login.LoginView;
import services.AuthService;
import javax.swing.JOptionPane;

public class LoginController {

    private LoginView view;
    private AuthService authService;

    public LoginController(LoginView view) {
        this.view = view;
        this.authService = new AuthService(); // Asume que AuthService es tu servicio de autenticación
    }

    public void handleLogin(String cedula, String contrasena) {
        // Aquí iría la lógica de autenticación
        // Por ejemplo, llamar a un servicio de autenticación
        if (authService.autenticar(cedula, contrasena)) {
            JOptionPane.showMessageDialog(view, "Inicio de sesión exitoso!");
            // Aquí podrías navegar a la siguiente vista
            
        } else {
            JOptionPane.showMessageDialog(view, "Cédula o contraseña incorrectos.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleBack() {
        // Lógica para volver a la pantalla anterior, si aplica
        JOptionPane.showMessageDialog(view, "Volver a la pantalla anterior.");
    }
}