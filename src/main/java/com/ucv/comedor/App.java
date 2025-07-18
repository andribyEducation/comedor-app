package com.ucv.comedor;
// import views.Registro_Comensal_view.RegistroComensalView;
import views.home.Home;

import javax.swing.*;

import controllers.home.HomeController;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Home view = new Home();
            new HomeController(view);
            // new AdminDashboardController(view);
            view.setVisible(true);
        });
    }
}
