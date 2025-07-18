package com.ucv.comedor;
import views.Registro_Comensal_view.RegistroComensalView;
<<<<<<< HEAD
import views.admin.dashboards.AdminDashboardView;
import views.home.Home;
import controllers.AdminDashboardController;
=======

>>>>>>> 64616df (registro general)
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
<<<<<<< HEAD
            Home view = new Home();
            // new AdminDashboardController(view);
=======
            RegistroComensalView view = new RegistroComensalView();
>>>>>>> 64616df (registro general)
            view.setVisible(true);
        });
    }
}
