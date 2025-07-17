package com.ucv.comedor;

import javax.swing.SwingUtilities;

import views.home.Home;
import controllers.HomeController;
import views.admin.dashboards.AdminDashboard;

public class App 
{
    public static void main( String[] args )
    {
         SwingUtilities.invokeLater(() -> {
           AdminDashboard adminDashboard = new AdminDashboard();
           adminDashboard.setVisible(true);
        });
    }
}
