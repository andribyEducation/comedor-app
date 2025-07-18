package com.ucv.comedor;
import views.admin.dashboards.AdminDashboardView;
import views.home.Home;
import controllers.AdminDashboardController;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminDashboardView view = new AdminDashboardView();
            new AdminDashboardController(view);
            view.setVisible(true);
        });
    }
}
