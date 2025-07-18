package com.ucv.comedor;
<<<<<<< HEAD
=======
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> b4f20e4 (Merge)
import views.Registro_Comensal_view.RegistroComensalView;
<<<<<<< HEAD
import views.admin.dashboards.AdminDashboardView;
import views.home.Home;
import controllers.AdminDashboardController;
<<<<<<< HEAD
=======

>>>>>>> 64616df (registro general)
=======
>>>>>>> 94619347654f87e930bfbf360b35178a9ad96adc
=======
import views.admin.dashboards.AdminDashboardView;
import views.home.Home;
import controllers.AdminDashboardController;
=======
import views.Registro_Comensal_view.RegistroComensalView;

>>>>>>> 64616df (registro general)
>>>>>>> 395627d (Merge)
>>>>>>> b4f20e4 (Merge)
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            Home view = new Home();
            // new AdminDashboardController(view);
=======
            RegistroComensalView view = new RegistroComensalView();
>>>>>>> 64616df (registro general)
=======
=======
>>>>>>> b4f20e4 (Merge)
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
<<<<<<< HEAD
>>>>>>> 40b99bc (Merge conflicts)
=======
=======
            Home view = new Home();
            // new AdminDashboardController(view);
=======
            RegistroComensalView view = new RegistroComensalView();
>>>>>>> 64616df (registro general)
>>>>>>> 395627d (Merge)
>>>>>>> b4f20e4 (Merge)
            view.setVisible(true);
        });
    }
}
