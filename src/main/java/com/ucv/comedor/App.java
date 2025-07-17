package com.ucv.comedor;

import javax.swing.SwingUtilities;
import views.home.Home;
import controllers.HomeController;
import views.admin.ValidationAdminView;

public class App 
{
    public static void main( String[] args )
    {
         SwingUtilities.invokeLater(() -> {
            /*Home homeView = new Home();
            new HomeController(homeView);
            homeView.setVisible(true);*/

            ValidationAdminView validationAdminView = new ValidationAdminView();
            validationAdminView.setVisible(true);

            
        });
    }
}
