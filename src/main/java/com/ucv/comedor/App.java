package com.ucv.comedor;
import views.admin.dashboards.AdminDashboardView;
import views.home.Home;
import controllers.AdminDashboardController;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Home view = new Home();
            // new AdminDashboardController(view);
            view.setVisible(true);
        });
    }
}
