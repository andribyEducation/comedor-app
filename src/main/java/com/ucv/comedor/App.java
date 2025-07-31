package com.ucv.comedor;

import com.ucv.views.admin.dashboards.AdminDashboardView;
import com.ucv.views.comensal.consultaMenu.ConsultaMenu;
import com.ucv.controllers.adminDashboard.AdminDashboardController;
import com.ucv.views.home.Home;
import com.ucv.controllers.home.HomeController;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            /*
             * Home view = new Home();
             * new HomeController(view);
             * view.setVisible(true);
             */

            AdminDashboardView view = new AdminDashboardView();
            new AdminDashboardController(view);
            view.setVisible(true);
        });
    }
}
