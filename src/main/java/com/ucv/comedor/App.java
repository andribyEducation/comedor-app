package com.ucv.comedor;
import com.ucv.views.admin.dashboards.AdminDashboardView;
import com.ucv.views.comensal.consultaMenu.ConsultaMenu;
import com.ucv.views.home.Home;
import com.ucv.controllers.adminDashboard.AdminDashboardController;
import com.ucv.controllers.home.HomeController;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            Home view = new Home();
            new HomeController(view);
            view.setVisible(true);

            //dashboard comensal
            //ConsultaMenu consultaMenu = new ConsultaMenu();
           //consultaMenu.setVisible(true);

        // AdminDashboardView adminView = new AdminDashboardView();
        // new AdminDashboardController(adminView);
        // adminView.setVisible(true);


        });
    }
}
