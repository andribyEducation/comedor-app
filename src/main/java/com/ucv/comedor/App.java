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
<<<<<<< HEAD
            Home view = new Home();
            // new AdminDashboardController(view);
=======
            RegistroComensalView view = new RegistroComensalView();
>>>>>>> 64616df (registro general)
=======
<<<<<<< HEAD
            RegistroComensalView view = new RegistroComensalView();
=======
            AdminDashboardView view = new AdminDashboardView();
            new AdminDashboardController(view);
>>>>>>> 94619347654f87e930bfbf360b35178a9ad96adc
=======
            Home view = new Home();
            // new AdminDashboardController(view);
>>>>>>> 97cc32c (Merge conflicts)
>>>>>>> 40b99bc (Merge conflicts)
            view.setVisible(true);
        });
    }
}
