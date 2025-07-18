package com.ucv.comedor;
<<<<<<< HEAD
import views.login.LoginView;
=======
import views.Registro_Comensal_view.RegistroComensalView;
>>>>>>> main
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
<<<<<<< HEAD
           LoginView loginView = new LoginView();
            loginView.setVisible(true);
=======
            RegistroComensalView view = new RegistroComensalView();
            // new AdminDashboardController(view);
            view.setVisible(true);
>>>>>>> main
        });
    }
}
