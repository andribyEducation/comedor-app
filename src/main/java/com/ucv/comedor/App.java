package com.ucv.comedor;
import views.admin.dashboards.AdminDashboard;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminDashboard view = new AdminDashboard();
            view.setVisible(true);
        });
    }
}
