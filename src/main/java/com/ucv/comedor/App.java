package com.ucv.comedor;
<<<<<<< HEAD
import views.Registro_Comensal_view.RegistroComensalView;

=======
import views.admin.dashboards.AdminDashboardView;
import controllers.AdminDashboardController;
>>>>>>> 94619347654f87e930bfbf360b35178a9ad96adc
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
<<<<<<< HEAD
            RegistroComensalView view = new RegistroComensalView();
=======
            AdminDashboardView view = new AdminDashboardView();
            new AdminDashboardController(view);
>>>>>>> 94619347654f87e930bfbf360b35178a9ad96adc
            view.setVisible(true);
        });
    }
}
